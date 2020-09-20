package com.mehmetbalbay.bitcointicker.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mehmetbalbay.bitcointicker.models.network.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.room.converters.*
import com.mehmetbalbay.bitcointicker.room.dao.CoinsMarketsDao

@Database(
    entities = [
        CurrencyItem::class,
        CoinDetailItem::class
    ], version = 1, exportSchema = false
)
@TypeConverters(
    value = [
        (MarketDataConverter::class),
        (DescriptionDataConverter::class),
        (ImageDataConverter::class),
        (PriceChange24hInCurrencyConverter::class),
        (MarketCapChange24hInCurrencyConverter::class),
        (MarketCapChangePercentage24hInCurrencyConverter::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinsMarketsDao(): CoinsMarketsDao
}