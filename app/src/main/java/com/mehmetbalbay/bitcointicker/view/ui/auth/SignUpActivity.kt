package com.mehmetbalbay.bitcointicker.view.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelActivity
import com.mehmetbalbay.bitcointicker.databinding.ActivitySignupBinding
import com.mehmetbalbay.bitcointicker.extension.gone
import com.mehmetbalbay.bitcointicker.extension.visible
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.utils.startPrefScreenActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SignUpActivity : ViewModelActivity(), HasAndroidInjector, AuthListener {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>
    private val viewModel: AuthViewModel by injectViewModels()
    private val binding: ActivitySignupBinding by binding(R.layout.activity_signup)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding.signUpViewModel = viewModel
        viewModel.authListener = this

        setToolbarViews()
        setChangeListeners()
        setListenersClickEvents()
    }

    private fun setToolbarViews() {
        binding.toolbar.title.text = getString(R.string.register_btn)
        binding.toolbar.centerLogo.gone()
        binding.toolbar.back.visible()
    }

    private fun setEmailChangeListener() {
        binding.sgnEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                viewModel.email = editable.toString()
            }
        })
    }

    private fun setPasswordChangeListener() {
        binding.sgnPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                viewModel.password = editable.toString()
            }
        })
    }

    private fun setConfirmPasswordChangeListener() {
        binding.sgnConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                viewModel.confirmPassword = editable.toString()
            }
        })
    }

    private fun setChangeListeners() {
        setEmailChangeListener()
        setPasswordChangeListener()
        setConfirmPasswordChangeListener()
    }

    private fun setListenersClickEvents() {
        binding.registerBtn.setOnClickListener {
            viewModel.signUp()
        }

        binding.toolbar.back.setOnClickListener { this.finish() }
    }

    override fun onStarted() {
        binding.progress.visible()
    }

    override fun onSuccess() {
        binding.progress.gone()
        SharedPreferenceHelper.saveSharedData(Const.IS_LOGIN, true)
        startPrefScreenActivity()
        finish()
    }

    override fun onFailure(message: String) {
        binding.progress.gone()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}