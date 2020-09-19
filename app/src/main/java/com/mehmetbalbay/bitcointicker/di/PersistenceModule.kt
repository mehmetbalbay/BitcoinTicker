package com.mehmetbalbay.bitcointicker.di

import android.app.Application
import androidx.room.Room
import com.mehmetbalbay.bitcointicker.room.AppDatabase
import com.mehmetbalbay.bitcointicker.room.dao.CoinsMarketsDao
import com.mehmetbalbay.bitcointicker.utils.Const
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            Const.BT_DB_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinsMarketsDao(database: AppDatabase): CoinsMarketsDao {
        return database.coinsMarketsDao()
    }
}