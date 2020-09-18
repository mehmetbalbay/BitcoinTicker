package com.mehmetbalbay.bitcointicker.utils

import android.content.Context
import android.content.Intent
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivity

fun Context.startMainActivity() =
    Intent(this, MainActivity::class.java).also {
        startActivity(it)
    }