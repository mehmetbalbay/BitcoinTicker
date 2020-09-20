package com.mehmetbalbay.bitcointicker.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mehmetbalbay.bitcointicker.models.network.MarketCapChange24hInCurrency

class MarketCapChange24hInCurrencyConverter {

    @TypeConverter
    fun stringToMarketCapChange24hInCurrency(value: String): MarketCapChange24hInCurrency? {
        return Gson().fromJson(value, MarketCapChange24hInCurrency::class.java)
    }

    @TypeConverter
    fun marketCapChange24hInCurrencyToString(description: MarketCapChange24hInCurrency?): String {
        return Gson().toJson(description)
    }
}