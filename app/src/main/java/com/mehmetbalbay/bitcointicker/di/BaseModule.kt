package com.mehmetbalbay.bitcointicker.di

import com.mehmetbalbay.bitcointicker.base.ViewModelActivity
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BaseModule {

    @ContributesAndroidInjector
    internal abstract fun contributeViewModelActivity(): ViewModelActivity

    @ContributesAndroidInjector
    internal abstract fun contributeViewModelFragment(): ViewModelFragment
}