package com.mehmetbalbay.bitcointicker.view.ui.coindetail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.google.firebase.auth.FirebaseUser
import com.mehmetbalbay.bitcointicker.base.LiveCoroutineViewModel
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.repository.MainCoinsRepository
import com.mehmetbalbay.bitcointicker.view.ui.mycoins.MyCoinsListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CoinDetailViewModel
@Inject
constructor(
    private val mainCoinRepository: MainCoinsRepository
) : LiveCoroutineViewModel() {

    private var currencyItem: MutableLiveData<String> = MutableLiveData()
    private var coinItemId: MutableLiveData<String> = MutableLiveData()
    var coinDetailLiveData: LiveData<Resource<CoinDetailItem>>

    var myCoinListener: MyCoinsListener? = null
    private val disposables = CompositeDisposable()
    val favourite = ObservableBoolean()
    private lateinit var coinDetailItem: CoinDetailItem

    init {
        Timber.d("Injection MainActivityViewModel")

        this.coinDetailLiveData = this.currencyItem.switchMap { currencyItem ->
            launchOnViewModelScope {
                this.mainCoinRepository.loadCoinsMarketsDetail(currencyItem)
            }
        }
    }

    fun refresh() {
        //movieRepository.
    }

    fun onAddFavoriteFireStore(firebaseUser: FirebaseUser, coinDetailItem: CoinDetailItem) {
        myCoinListener?.onStarted()
        val disposable = mainCoinRepository.dataAddCoinFavorite(firebaseUser, coinDetailItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                myCoinListener?.onSuccess()
            }, {
                it.message?.run {
                    myCoinListener?.onFailure(this)
                }
            })
        disposables.add(disposable)
    }

    fun postCoinDetailId(currencyItemId: String) = this.currencyItem.postValue(currencyItemId)

    //fun onClickedFavourite(coinDetailItem: CoinDetailItem) = favourite.set(mainCoinRepository.onClickFavourite(coinDetailItem))
}