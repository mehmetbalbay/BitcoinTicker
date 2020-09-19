package com.mehmetbalbay.bitcointicker.api

import androidx.lifecycle.LiveData
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface BTService {

    @GET("coins/markets")
    @Headers("Accept: application/json")
    fun fetchCoinsMarkets(@QueryMap map: HashMap<String, Any>): LiveData<ApiResponse<List<CurrencyItem>>>

}