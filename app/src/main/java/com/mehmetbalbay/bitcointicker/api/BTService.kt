package com.mehmetbalbay.bitcointicker.api

import androidx.lifecycle.LiveData
import com.mehmetbalbay.bitcointicker.models.network.CoinsMarketsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface BTService {

    @GET("coins/markets")
    fun fetchCoinsMarkets(@QueryMap map: HashMap<String, Any>): LiveData<ApiResponse<CoinsMarketsResponse>>

}