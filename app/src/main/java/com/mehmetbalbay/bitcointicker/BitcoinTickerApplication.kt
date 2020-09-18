package com.mehmetbalbay.bitcointicker

import android.app.Application
import com.mehmetbalbay.bitcointicker.utils.AppFonts

class BitcoinTickerApplication : Application() {

    //private val appComponent = DaggerAppComp.fac

    override fun onCreate() {
        super.onCreate()

        AppFonts.initFonts(applicationContext)
    }
}