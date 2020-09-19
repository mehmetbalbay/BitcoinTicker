package com.mehmetbalbay.bitcointicker.models.entity


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class Roi(
    @SerializedName("times")
    val times: Double,
    @SerializedName("currency")
    @PrimaryKey
    val currency: String,
    @SerializedName("percentage")
    val percentage: Double
) : Parcelable