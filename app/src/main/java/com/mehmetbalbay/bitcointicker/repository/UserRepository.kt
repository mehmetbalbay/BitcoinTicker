package com.mehmetbalbay.bitcointicker.repository

import com.mehmetbalbay.bitcointicker.data.FirebaseSource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor(
    private val firebase: FirebaseSource
) : Repository {

    override var isLoading: Boolean = false

    init {
        Timber.d("Injection User Repository")
    }

    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

}