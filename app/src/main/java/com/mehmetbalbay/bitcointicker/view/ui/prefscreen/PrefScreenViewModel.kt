package com.mehmetbalbay.bitcointicker.view.ui.prefscreen

import com.mehmetbalbay.bitcointicker.base.LiveCoroutineViewModel
import timber.log.Timber
import javax.inject.Inject

class PrefScreenViewModel @Inject
constructor(
    private val prefScreenRepository: PrefScreenRepository
) : LiveCoroutineViewModel() {

    init {
        Timber.d("Injection PrefScreenViewModel")
    }

}