package com.mehmetbalbay.bitcointicker.view.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import com.mehmetbalbay.bitcointicker.databinding.FragmentSettingsBinding
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivityViewModel

class SettingsFragment : ViewModelFragment() {

    private val viewModel: MainActivityViewModel by injectActivityViewModels()
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
    }

}