package com.mehmetbalbay.bitcointicker.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mehmetbalbay.bitcointicker.models.network.PriceChange24hInCurrency

class PriceChange24hInCurrencyConverter {

    @TypeConverter
    fun stringToPriceChange24hInCurrency(value: String): PriceChange24hInCurrency? {
        return Gson().fromJson(value, PriceChange24hInCurrency::class.java)
    }

    @TypeConverter
    fun priceChange24hInCurrencyToString(description: PriceChange24hInCurrency?): String {
        return Gson().toJson(description)
    }
}