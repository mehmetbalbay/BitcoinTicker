package com.mehmetbalbay.bitcointicker.view.ui.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}