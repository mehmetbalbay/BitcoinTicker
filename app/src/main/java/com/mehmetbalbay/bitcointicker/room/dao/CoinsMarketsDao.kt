package com.mehmetbalbay.bitcointicker.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem

@Dao
interface CoinsMarketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinsMarkets(coinsMarkets: List<CurrencyItem>)

    @Query("SELECT * FROM CurrencyItem")
    fun getCoinsMarkets(): LiveData<List<CurrencyItem>>

    @Query("SELECT * FROM CurrencyItem WHERE name LIKE '%' || :searchKey || '%' OR '%' || :searchKey || '%'")
    fun searchCoinsMarkets(searchKey: String): LiveData<List<CurrencyItem>>

    @Query("SELECT * FROM CurrencyItem")
    fun searchFullCoinsMarkets(): LiveData<List<CurrencyItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCoinDetail(coinDetailItem: CoinDetailItem)

    @Query("SELECT * FROM CoinDetailItem WHERE id = :coinItemId")
    fun getCoinsMarketsDetail(coinItemId: String): LiveData<CoinDetailItem>

}