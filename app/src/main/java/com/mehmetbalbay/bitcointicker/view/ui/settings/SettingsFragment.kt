package com.mehmetbalbay.bitcointicker.view.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivityViewModel

class SettingsFragment : ViewModelFragment() {

    private val viewModel: MainActivityViewModel by injectActivityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

}