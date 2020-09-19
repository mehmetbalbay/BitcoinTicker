package com.mehmetbalbay.bitcointicker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mehmetbalbay.bitcointicker.di.annotations.ViewModelKey
import com.mehmetbalbay.bitcointicker.factory.AppViewModelFactory
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
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}