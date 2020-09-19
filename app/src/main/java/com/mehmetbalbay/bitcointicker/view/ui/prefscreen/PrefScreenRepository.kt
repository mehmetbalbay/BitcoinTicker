package com.mehmetbalbay.bitcointicker.view.ui.prefscreen

import com.mehmetbalbay.bitcointicker.repository.Repository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefScreenRepository @Inject
constructor(

) : Repository {

    override var isLoading: Boolean = false

    init {
        Timber.d("Injection PrefScreenRepository")
    }

}