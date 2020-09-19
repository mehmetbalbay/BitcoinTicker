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
    val coinsMarketsLiveData: LiveData<Resource<List<CurrencyItem>>>

    var fetchStatus = FetchStatus()
        private set

    init {
        Timber.d("Injection MainActivityViewModel")

        this.coinsMarketsLiveData = this.coinsMarketsPage.switchMap { page ->
            launchOnViewModelScope {
                this.mainCoinsRepository.loadCoinsMarkets(page)
            }
        }
    }

    fun fetchStatus(resource: Resource<List<CurrencyItem>>) {
        fetchStatus = resource.fetchStatus
    }

    fun postCoinsMarketsPage(page: Int) = this.coinsMarketsPage.postValue(page)

}