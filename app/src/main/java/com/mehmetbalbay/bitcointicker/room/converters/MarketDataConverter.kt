package com.mehmetbalbay.bitcointicker.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mehmetbalbay.bitcointicker.models.network.MarketData

class MarketDataConverter {
    @TypeConverter
    fun stringToMarket(value: String): MarketData? {
        return Gson().fromJson(value, MarketData::class.java)
    }

    @TypeConverter
    fun marketToString(market: MarketData?): String {
        return Gson().toJson(market)
    }
}