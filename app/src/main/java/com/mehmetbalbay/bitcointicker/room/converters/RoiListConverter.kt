package com.mehmetbalbay.bitcointicker.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mehmetbalbay.bitcointicker.models.entity.Roi

open class RoiListConverter {

    @TypeConverter
    fun stringToRoi(value: String): Roi? {
        return Gson().fromJson(value, Roi::class.java)
    }

    @TypeConverter
    fun roiToString(roi: Roi?): String {
        return Gson().toJson(roi)
    }

}