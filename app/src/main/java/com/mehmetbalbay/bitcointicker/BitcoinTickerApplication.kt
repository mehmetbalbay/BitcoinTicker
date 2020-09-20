package com.mehmetbalbay.bitcointicker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.viewbinding.BuildConfig
import com.mehmetbalbay.bitcointicker.di.DaggerAppComponent
import com.mehmetbalbay.bitcointicker.utils.AppFonts
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


class BitcoinTickerApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)

    companion object {
        const val CHANNEL_ID = "exampleServiceChannel"
    }

    override fun onCreate() {
        super.onCreate()

        AppFonts.initFonts(applicationContext)
        Hawk.init(applicationContext).build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        createNotificationChannel()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )!!
            manager.createNotificationChannel(serviceChannel)
        }
    }
}