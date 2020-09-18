package com.mehmetbalbay.bitcointicker.utils

import android.content.Context
import android.graphics.Typeface

object AppFonts {

    lateinit var MontserratBold: Typeface
    lateinit var MontserratRegular: Typeface

    fun initFonts(context: Context) {
        MontserratRegular = Typeface.createFromAsset(context.assets, "fonts/Montserrat_Regular.ttf")
        MontserratBold = Typeface.createFromAsset(context.assets, "fonts/Montserrat_Bold.ttf")
    }

}