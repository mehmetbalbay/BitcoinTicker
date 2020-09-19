package com.mehmetbalbay.bitcointicker.utils

import android.util.Patterns

class Validation {
    companion object {
        fun isValidEmail(target: String): Boolean {
            return if (target.isEmpty()) {
                false
            } else {
                Patterns.EMAIL_ADDRESS.matcher(target).matches()
            }
        }
    }
}