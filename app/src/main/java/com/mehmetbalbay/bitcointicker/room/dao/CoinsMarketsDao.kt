package com.mehmetbalbay.bitcointicker.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem

@Dao
interface CoinsMarketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinsMarkets(coinsMarkets: List<CurrencyItem>)

    @Query("SELECT * FROM CurrencyItem")
    fun getCoinsMarkets(): LiveData<List<CurrencyItem>>

}