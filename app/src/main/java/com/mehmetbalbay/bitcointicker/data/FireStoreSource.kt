package com.mehmetbalbay.bitcointicker.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.utils.Const
import io.reactivex.Completable
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
            saveDataParam[Const.coinDetailItem] = coinDetailItem
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
}