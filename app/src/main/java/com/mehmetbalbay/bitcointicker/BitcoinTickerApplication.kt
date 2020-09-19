package com.mehmetbalbay.bitcointicker

import androidx.viewbinding.BuildConfig
import com.mehmetbalbay.bitcointicker.di.DaggerAppComponent
import com.mehmetbalbay.bitcointicker.utils.AppFonts
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class BitcoinTickerApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)


    override fun onCreate() {
        super.onCreate()

        AppFonts.initFonts(applicationContext)
        Hawk.init(applicationContext).build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}