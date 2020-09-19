package com.mehmetbalbay.bitcointicker.data

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSource @Inject
constructor() {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful) {
                    emitter.onComplete()
                } else {
                    it.exception?.run {
                        emitter.onError(this)
                    }
                }
            }
        }
    }


    fun register(email: String?, password: String?) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(it.exception!!)
                    }
                } else {
                    emitter.onError(it.exception!!)
                }
            }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}



