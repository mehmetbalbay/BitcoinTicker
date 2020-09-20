package com.mehmetbalbay.bitcointicker.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.mehmetbalbay.bitcointicker.base.LiveCoroutineViewModel
import com.mehmetbalbay.bitcointicker.models.FetchStatus
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.repository.MainCoinsRepository
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject
constructor(
    private val mainCoinsRepository: MainCoinsRepository
) : LiveCoroutineViewModel() {

    private var coinsMarketsPage: MutableLiveData<Int> = MutableLiveData()
    private var coinsSearchMarketsPage: MutableLiveData<Int> = MutableLiveData()
    private var searchKeyCoinsMarkets: MutableLiveData<String> = MutableLiveData()
    val coinsMarketsLiveData: LiveData<Resource<List<CurrencyItem>>>
    var searchCoinsMarketsLiveData: LiveData<List<CurrencyItem>>

    var fetchStatus = FetchStatus()
        private set

    init {
        Timber.d("Injection MainActivityViewModel")

        this.coinsMarketsLiveData = this.coinsMarketsPage.switchMap { page ->
            launchOnViewModelScope {
                this.mainCoinsRepository.loadCoinsMarkets(page)
            }
        }

        this.searchCoinsMarketsLiveData = this.searchKeyCoinsMarkets.switchMap { searchKey ->
            launchOnViewModelScope {
                this.mainCoinsRepository.getSearchCoinsMarketsList(searchKey)
            }
        }

        this.searchCoinsMarketsLiveData = launchOnViewModelScope {
            this.mainCoinsRepository.getCoinsMarketsList()
        }
    }

    fun fetchStatus(resource: Resource<List<CurrencyItem>>) {
        fetchStatus = resource.fetchStatus
    }

    fun postCoinsMarketsPage(page: Int) = this.coinsMarketsPage.postValue(page)

    fun postSearchCoinsMarketsPage(searchKey: String) =
        this.mainCoinsRepository.getSearchCoinsMarketsList(searchKey)

    fun postSearchFullCoinsMarketsPage() =
        this.mainCoinsRepository.getCoinsMarketsList()


}