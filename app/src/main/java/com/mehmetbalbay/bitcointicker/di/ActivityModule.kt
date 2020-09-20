package com.mehmetbalbay.bitcointicker.di

import com.mehmetbalbay.bitcointicker.di.annotations.ActivityScope
import com.mehmetbalbay.bitcointicker.view.ui.auth.LoginActivity
import com.mehmetbalbay.bitcointicker.view.ui.auth.SignUpActivity
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivity
import com.mehmetbalbay.bitcointicker.view.ui.prefscreen.PrefScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributePrefScreenActivity(): PrefScreenActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeSignUpActivity(): SignUpActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeLoginActivity(): LoginActivity

}