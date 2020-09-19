package com.mehmetbalbay.bitcointicker.di

import com.mehmetbalbay.bitcointicker.di.annotations.FragmentScope
import com.mehmetbalbay.bitcointicker.view.ui.main.MarketFragment
import com.mehmetbalbay.bitcointicker.view.ui.mycoins.MyCoinsFragment
import com.mehmetbalbay.bitcointicker.view.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeMarketFragment(): MarketFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeMyCoinsFragment(): MyCoinsFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): SettingsFragment

}