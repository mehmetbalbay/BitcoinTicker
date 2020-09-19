package com.mehmetbalbay.bitcointicker.view.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.utils.startMainActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_REMAIN_COUNT = 1500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
            this.finish()
        }, SPLASH_REMAIN_COUNT.toLong())
    }

}