package com.mehmetbalbay.bitcointicker.view.ui.auth

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehmetbalbay.bitcointicker.repository.UserRepository
import com.mehmetbalbay.bitcointicker.utils.Validation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AuthViewModel @Inject
constructor(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthListener? = null

    private var isLogout: MutableLiveData<Boolean> = MutableLiveData()

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    init {
        Timber.d("Injection AuthViewModel")
    }

    fun login() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        authListener?.onStarted()

        val disposable = repository.login(email = email!!, password = password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                it.message?.run {
                    authListener?.onFailure(this)
                }
            })
        disposables.add(disposable)
    }

    fun signUp() {
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Please enter a email")
            return
        }

        if (!Validation.isValidEmail(email!!)) {
            authListener?.onFailure("Please enter a valid email")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter a password")
            return
        }

        if (!password.equals(confirmPassword)) {
            authListener?.onFailure("Not Correcting Passwords")
            return
        }

        authListener?.onStarted()
        val disposable = repository.register(email = email!!, password = password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun logout(view: View) {
        repository.logout()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}