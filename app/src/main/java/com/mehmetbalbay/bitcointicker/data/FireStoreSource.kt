package com.mehmetbalbay.bitcointicker.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.utils.Const
import io.reactivex.Completable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FireStoreSource @Inject
constructor() {

    private val firebaseStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun addCoinFavorite(firebaseUser: FirebaseUser, coinDetailItem: CoinDetailItem) =
        Completable.create { emitter ->
            val documentRefFavorite =
                firebaseStore.collection(Const.myCoinCollectionName).document(firebaseUser.uid)

            val collectionMyFavorite = documentRefFavorite.collection(Const.myFavoriteList)

            val saveDataParam = HashMap<String, Any>()
            saveDataParam[Const.coinHashingAlgorithm] = coinDetailItem
            if (!emitter.isDisposed) {
                collectionMyFavorite.document()
                    .set(saveDataParam)
                    .addOnSuccessListener {
                        emitter.onComplete()
                    }
                    .addOnFailureListener {
                        emitter.onError(it)
                    }
            }
        }

    fun getMyCoinFavoriteList(firebaseUser: FirebaseUser) =
        Completable.create { emitter ->
            val documentRefFavorite =
                firebaseStore.collection(Const.myCoinCollectionName).document(firebaseUser.uid)
            val collectionMyFavorite = documentRefFavorite.collection(Const.myFavoriteList)
            if (!emitter.isDisposed) {
                collectionMyFavorite.get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val list: MutableList<CoinDetailItem> = ArrayList()
                        /*
                        for (document in it.getResult()) {
                            list.add(document.id)
                        }

                         */
                    } else {
                        Timber.d(it.exception, "Error getting documents: ")
                    }

                }
            }
        }
}