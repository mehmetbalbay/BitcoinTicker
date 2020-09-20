package com.mehmetbalbay.bitcointicker.view.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.utils.startLoginActivity
import com.mehmetbalbay.bitcointicker.utils.startPrefScreenActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_REMAIN_COUNT = 1500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({

            val isLogin =
                SharedPreferenceHelper.getSharedData(Const.IS_LOGIN) as Boolean?
            if (isLogin != null && isLogin) {
                startPrefScreenActivity()
            } else {
                startLoginActivity()
            }
            this.finish()
        }, SPLASH_REMAIN_COUNT.toLong())
    }

}