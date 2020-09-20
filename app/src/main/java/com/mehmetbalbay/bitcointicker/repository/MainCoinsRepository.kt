package com.mehmetbalbay.bitcointicker.repository

import androidx.lifecycle.LiveData
import com.mehmetbalbay.bitcointicker.api.ApiResponse
import com.mehmetbalbay.bitcointicker.api.BTService
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.models.Envelope
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.room.dao.CoinsMarketsDao
import com.mehmetbalbay.bitcointicker.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class MainCoinsRepository @Inject
constructor(
    private val coinsMarketsDao: CoinsMarketsDao,
    private val btService: BTService
) : Repository {

    override var isLoading: Boolean = false

    init {
        Timber.d("Injection MainRepository")
    }

    suspend fun loadCoinsMarketsDetail(
        coinItemId: String
    ): LiveData<Resource<CoinDetailItem>> =
        withContext(Dispatchers.IO) {
            return@withContext object :
                NetworkBoundRepository<CoinDetailItem, CoinDetailItem>() {
                override fun saveFetchData(items: CoinDetailItem) {
                    items.let {
                        coinsMarketsDao.updateCoinDetail(it)
                    }
                }

                override fun shouldFetch(data: CoinDetailItem?): Boolean {
                    return data == null
                }

                override fun loadFromDb(): LiveData<CoinDetailItem> {
                    return getCoinsMarketsDetail(coinItemId)
                }

                override fun fetchService(): LiveData<ApiResponse<CoinDetailItem>> {
                    return btService.fetchCoinsDetail(coinItemId)
                }

                override fun onFetchFailed(envelope: Envelope?) {
                    Timber.d("onFetchFailed : $envelope")
                }

            }.asLiveData()
        }

    suspend fun loadCoinsMarkets(page: Int): LiveData<Resource<List<CurrencyItem>>> =
        withContext(Dispatchers.IO) {
            return@withContext object :
                NetworkBoundRepository<List<CurrencyItem>, List<CurrencyItem>>() {
                override fun saveFetchData(items: List<CurrencyItem>) {
                    items.let {
                        coinsMarketsDao.insertCoinsMarkets(it)
                    }
                }

                override fun shouldFetch(data: List<CurrencyItem>?): Boolean {
                    return data == null || data.isEmpty()
                }

                override fun loadFromDb(): LiveData<List<CurrencyItem>> {
                    return getCoinsMarketsList()
                }

                override fun fetchService(): LiveData<ApiResponse<List<CurrencyItem>>> {
                    val map = HashMap<String, Any>()
                    val defaultCurrency =
                        SharedPreferenceHelper.getSharedData(Const.DEFAULT_CURRENCY) as String?
                    defaultCurrency?.let {
                        map[Const.vsCurrency] = it.toLowerCase(Locale.ROOT)
                    }
                    map[Const.order] = "market_cap_desc"
                    map[Const.page] = page
                    map[Const.perPage] = "20"
                    map[Const.sparkline] = false
                    map[Const.priceChangePercentage] = "24h"
                    return btService.fetchCoinsMarkets(map)
                }

                override fun onFetchFailed(envelope: Envelope?) {
                    Timber.d("onFetchFailed : $envelope")
                }

            }.asLiveData()
        }

    fun getCoinsMarketsList() = coinsMarketsDao.getCoinsMarkets()

    fun getSearchCoinsMarketsList(searchKey: String) = coinsMarketsDao.searchCoinsMarkets(searchKey)

    fun getCoinsMarketsDetail(coinItemId: String) =
        coinsMarketsDao.getCoinsMarketsDetail(coinItemId)
}