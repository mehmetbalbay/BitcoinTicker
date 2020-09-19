package com.mehmetbalbay.bitcointicker.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import com.mehmetbalbay.bitcointicker.databinding.FragmentMarketBinding
import com.mehmetbalbay.bitcointicker.extension.visible
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.view.adapter.CoinsMarketsAdapter
import com.mehmetbalbay.bitcointicker.view.ui.viewholder.coinsmarkets.CoinsMarketsViewHolder

class MarketFragment : ViewModelFragment(), CoinsMarketsViewHolder.Delegate {

    private val viewModel: MainActivityViewModel by injectActivityViewModels()
    private lateinit var binding: FragmentMarketBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentMarketBinding>(
            inflater, R.layout.fragment_market, container
        ).apply {
            binding = this
            viewModel = this@MarketFragment.viewModel
            lifecycleOwner = this@MarketFragment
            coinsMarketsAdapter = CoinsMarketsAdapter(this@MarketFragment)
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        loadCoinsMarkets(1)

        observeCoinsMarketsResource()
    }

    private fun initializeUI() {
        setToolbarItemsVisibility()
    }

    private fun setToolbarItemsVisibility() {
        binding.toolbar.notification.visible()
        binding.toolbar.search.visible()
    }

    private fun observeCoinsMarketsResource() {
        viewModel.coinsMarketsLiveData.observe(viewLifecycleOwner, {

        })
    }

    private fun loadCoinsMarkets(page: Int) = this.viewModel.postCoinsMarketsPage(page)

    override fun onItemClick(currentItem: CurrencyItem, view: View) {
        Toast.makeText(requireContext(), "Test", Toast.LENGTH_LONG).show()
    }

}