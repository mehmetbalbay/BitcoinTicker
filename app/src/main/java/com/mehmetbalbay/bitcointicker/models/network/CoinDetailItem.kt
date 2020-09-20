package com.mehmetbalbay.bitcointicker.models.network

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class CoinDetailItem(
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("market_data")
    val marketData: MarketData,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String?,
    @SerializedName("description")
    val description: Description?
) : Parcelable