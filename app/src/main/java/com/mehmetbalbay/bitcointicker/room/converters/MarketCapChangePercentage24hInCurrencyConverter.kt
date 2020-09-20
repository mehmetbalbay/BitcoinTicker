package com.mehmetbalbay.bitcointicker.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mehmetbalbay.bitcointicker.models.network.MarketCapChangePercentage24hInCurrency

class MarketCapChangePercentage24hInCurrencyConverter {

    @TypeConverter
    fun stringToMarketCapChangePercentage24hInCurrency(value: String): MarketCapChangePercentage24hInCurrency? {
        return Gson().fromJson(value, MarketCapChangePercentage24hInCurrency::class.java)
    }

    @TypeConverter
    fun marketCapChangePercentage24hInCurrencyToString(description: MarketCapChangePercentage24hInCurrency?): String {
        return Gson().toJson(description)
    }
}