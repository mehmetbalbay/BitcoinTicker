package com.mehmetbalbay.bitcointicker.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mehmetbalbay.bitcointicker.models.network.Description

class DescriptionDataConverter {
    @TypeConverter
    fun stringToDescription(value: String): Description? {
        return Gson().fromJson(value, Description::class.java)
    }

    @TypeConverter
    fun descriptionToString(description: Description?): String {
        return Gson().toJson(description)
    }
}