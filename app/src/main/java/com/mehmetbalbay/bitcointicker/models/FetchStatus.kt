package com.mehmetbalbay.bitcointicker.models

data class FetchStatus(
    var isOnLoading: Boolean = false,
    var isOnSuccess: Boolean = false,
    var isOnError: Boolean = false,
    var isOnLast: Boolean = false,
    var nextPage: Int? = null
)