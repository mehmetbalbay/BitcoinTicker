package com.mehmetbalbay.bitcointicker.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.room.converters.DescriptionDataConverter
import com.mehmetbalbay.bitcointicker.room.converters.ImageDataConverter
import com.mehmetbalbay.bitcointicker.room.converters.MarketDataConverter
import com.mehmetbalbay.bitcointicker.room.converters.PriceChange24hInCurrencyConverter
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
        (PriceChange24hInCurrencyConverter::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinsMarketsDao(): CoinsMarketsDao
}