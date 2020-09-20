package com.mehmetbalbay.bitcointicker.view.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelActivity
import com.mehmetbalbay.bitcointicker.databinding.ActivityMainBinding
import com.mehmetbalbay.bitcointicker.helper.BackgroundRefreshService
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : ViewModelActivity(), HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>
    private val viewModel: MainActivityViewModel by injectViewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        initializeUI()

        startBackgroundService()
    }

    private fun startBackgroundService() {
        val serviceIntent = Intent(this, BackgroundRefreshService::class.java)
        serviceIntent.putExtra("isStart", true)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService() {
        val serviceIntent = Intent(this, BackgroundRefreshService::class.java)
        serviceIntent.putExtra("isStart", false)
        stopService(serviceIntent)
    }

    private fun initializeUI() {
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.mainBottomNavigation, navController)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}