package com.mehmetbalbay.bitcointicker.models.network


import com.google.gson.annotations.SerializedName

class Market(
    @SerializedName("name")
    val name: String,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("has_trading_incentive")
    val hasTradingIncentive: Boolean
)