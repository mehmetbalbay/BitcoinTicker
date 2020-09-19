package com.mehmetbalbay.bitcointicker.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mehmetbalbay.bitcointicker.models.entity.CoinsMarkets
import com.mehmetbalbay.bitcointicker.models.entity.Roi
import com.mehmetbalbay.bitcointicker.room.converters.RoiListConverter
import com.mehmetbalbay.bitcointicker.room.dao.CoinsMarketsDao

@Database(
    entities = [
        CoinsMarkets::class,
        Roi::class
    ], version = 1, exportSchema = false
)
@TypeConverters(
    value = [
        (RoiListConverter::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinsMarketsDao(): CoinsMarketsDao
}