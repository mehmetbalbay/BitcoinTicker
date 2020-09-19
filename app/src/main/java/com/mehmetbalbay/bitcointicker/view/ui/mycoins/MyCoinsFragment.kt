package com.mehmetbalbay.bitcointicker.view.ui.mycoins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import com.mehmetbalbay.bitcointicker.databinding.FragmentMyCoinsBinding
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivityViewModel

class MyCoinsFragment : ViewModelFragment() {

    private val viewModel: MainActivityViewModel by injectActivityViewModels()
    private lateinit var binding: FragmentMyCoinsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentMyCoinsBinding>(
            inflater, R.layout.fragment_my_coins, container
        ).apply {
            binding = this
            viewModel = this@MyCoinsFragment.viewModel
            lifecycleOwner = this@MyCoinsFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(binding.toolbar.title, getString(R.string.my_coins_fragment))
    }

}