package com.mehmetbalbay.bitcointicker.view.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import com.mehmetbalbay.bitcointicker.databinding.FragmentSettingsBinding
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.utils.startLoginActivity
import com.mehmetbalbay.bitcointicker.view.ui.auth.AuthViewModel

class SettingsFragment : ViewModelFragment() {

    private val viewModel: AuthViewModel by injectActivityViewModels()
    private val authViewModel: AuthViewModel by injectViewModels()
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentSettingsBinding>(
            inflater, R.layout.fragment_settings, container
        ).apply {
            binding = this
            viewModel = this@SettingsFragment.viewModel
            lifecycleOwner = this@SettingsFragment
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(binding.toolbar.title, getString(R.string.settings_fragment))
        setClickEventListeners()
        setUserData()
    }

    private fun setUserData() {
        val firebaseUser = authViewModel.user
        firebaseUser?.let {
            val welcomeText = "Welcome ${it.displayName}"
            val email = "Email: ${it.email}"
            binding.welcomeText.text = welcomeText
            binding.email.text = email
        }
    }

    private fun setClickEventListeners() {
        binding.logout.setOnClickListener {
            viewModel.logout()
            SharedPreferenceHelper.saveSharedData(Const.IS_LOGIN, false)
            requireActivity().startLoginActivity()
            requireActivity().finish()
        }
    }

}