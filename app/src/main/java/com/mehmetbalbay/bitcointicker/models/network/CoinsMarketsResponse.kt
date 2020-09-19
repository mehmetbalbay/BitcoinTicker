package com.mehmetbalbay.bitcointicker.models.network


import android.os.Parcelable
import com.mehmetbalbay.bitcointicker.models.entity.CoinsMarkets
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinsMarketsResponse(
    val coinMarkets: List<CoinsMarkets>?
) : Parcelable