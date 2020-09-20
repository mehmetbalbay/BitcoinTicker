package com.mehmetbalbay.bitcointicker.utils

import android.content.Context
import android.content.Intent
import com.mehmetbalbay.bitcointicker.view.ui.auth.LoginActivity
import com.mehmetbalbay.bitcointicker.view.ui.auth.SignUpActivity
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivity
import com.mehmetbalbay.bitcointicker.view.ui.prefscreen.PrefScreenActivity

fun Context.startMainActivity() =
    Intent(this, MainActivity::class.java).also {
        startActivity(it)
    }

fun Context.startPrefScreenActivity() =
    Intent(this, PrefScreenActivity::class.java).also {
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        startActivity(it)
    }

fun Context.startSignUpActivity() =
    Intent(this, SignUpActivity::class.java).also {
        startActivity(it)
    }