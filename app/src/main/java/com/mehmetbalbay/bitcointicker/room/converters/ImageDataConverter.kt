package com.mehmetbalbay.bitcointicker.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mehmetbalbay.bitcointicker.models.network.Image

class ImageDataConverter {
    @TypeConverter
    fun stringToImage(value: String): Image? {
        return Gson().fromJson(value, Image::class.java)
    }

    @TypeConverter
    fun imageToString(image: Image?): String {
        return Gson().toJson(image)
    }
}