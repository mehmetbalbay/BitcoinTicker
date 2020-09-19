package com.mehmetbalbay.bitcointicker.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.room.dao.CoinsMarketsDao

@Database(
    entities = [
        CurrencyItem::class
    ], version = 1, exportSchema = false
)
@TypeConverters(
    value = [

    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinsMarketsDao(): CoinsMarketsDao
}