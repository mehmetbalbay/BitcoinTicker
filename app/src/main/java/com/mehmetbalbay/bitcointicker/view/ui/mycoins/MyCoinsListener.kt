package com.mehmetbalbay.bitcointicker.view.ui.mycoins

interface MyCoinsListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}