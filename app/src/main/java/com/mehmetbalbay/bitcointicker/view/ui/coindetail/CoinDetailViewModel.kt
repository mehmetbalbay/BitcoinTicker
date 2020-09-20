package com.mehmetbalbay.bitcointicker.view.ui.coindetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.mehmetbalbay.bitcointicker.base.LiveCoroutineViewModel
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.network.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.repository.MainCoinsRepository
import timber.log.Timber
import javax.inject.Inject

class CoinDetailViewModel
@Inject
constructor(
    private val mainCoinRepository: MainCoinsRepository
) : LiveCoroutineViewModel() {

    private var currencyItem: MutableLiveData<String> = MutableLiveData()
    var coinDetailLiveData: LiveData<Resource<CoinDetailItem>>

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

    fun postCoinDetailId(currencyItem: CurrencyItem) = this.currencyItem.postValue(currencyItem.id)

    fun postCoinDetail(currencyItemId: String, isCoinDetail: Boolean) =
        mainCoinRepository.getCoinsMarketsDetail(currencyItemId)

}