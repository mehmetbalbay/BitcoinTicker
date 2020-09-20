package com.mehmetbalbay.bitcointicker.view.ui.auth

import android.os.Bundle
import android.widget.Toast
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelActivity
import com.mehmetbalbay.bitcointicker.databinding.ActivityLoginBinding
import com.mehmetbalbay.bitcointicker.extension.gone
import com.mehmetbalbay.bitcointicker.extension.visible
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.utils.startPrefScreenActivity
import com.mehmetbalbay.bitcointicker.utils.startSignUpActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class LoginActivity : ViewModelActivity(), HasAndroidInjector, AuthListener {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>
    private val viewModel: AuthViewModel by injectViewModels()
    private val binding: ActivityLoginBinding by binding(R.layout.activity_login)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.authViewModel = viewModel
        viewModel.authListener = this
        setToolbarViews()
        setListenerClickEvents()
    }

    private fun setToolbarViews() {
        binding.toolbar.title.text = getString(R.string.login_title)
        binding.toolbar.centerLogo.gone()
        binding.toolbar.back.visible()
    }

    private fun setListenerClickEvents() {
        binding.loginBtn.setOnClickListener {
            viewModel.login()
        }

        binding.signUp.setOnClickListener {
            startSignUpActivity()
        }

        binding.toolbar.back.setOnClickListener { this.finish() }
    }

    override fun onStarted() {
        binding.progressbar.visible()
    }

    override fun onSuccess() {
        binding.progressbar.gone()
        SharedPreferenceHelper.saveSharedData(Const.IS_LOGIN, true)
        startPrefScreenActivity()
        this.finish()
    }

    override fun onFailure(message: String) {
        binding.progressbar.gone()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }

}