package com.mehmetbalbay.bitcointicker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mehmetbalbay.bitcointicker.di.annotations.ViewModelKey
import com.mehmetbalbay.bitcointicker.factory.AppViewModelFactory
import com.mehmetbalbay.bitcointicker.view.ui.auth.AuthViewModel
import com.mehmetbalbay.bitcointicker.view.ui.coindetail.CoinDetailViewModel
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindAuthDetailViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    internal abstract fun bindCoinDetailViewModel(coinDetailViewModel: CoinDetailViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}