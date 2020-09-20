package com.mehmetbalbay.bitcointicker.view.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import com.mehmetbalbay.bitcointicker.databinding.FragmentMarketBinding
import com.mehmetbalbay.bitcointicker.enums.Status
import com.mehmetbalbay.bitcointicker.extension.gone
import com.mehmetbalbay.bitcointicker.extension.visible
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.view.adapter.coinsmarkets.CoinsMarketsAdapter
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

        setClickListeners()
        setSearchTextListeners()
        observeCoinsMarketsResource()
    }

    private fun setSearchTextListeners() {
        binding.toolbar.txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val searchKey: String = s.toString().trim()
                if (searchKey.isNotEmpty()) {
                    searchCoinsMarkets(searchKey).observe(viewLifecycleOwner, {
                        it?.run {
                            binding.coinsMarketsAdapter!!.setData(this)
                        }
                    })
                } else {
                    searchFullCoinsMarkets().observe(viewLifecycleOwner, {
                        it?.run {
                            binding.coinsMarketsAdapter!!.setData(this)
                        }
                    })
                }
            }
        })
    }

    private fun setClickListeners() {
        binding.toolbar.search.setOnClickListener {
            binding.toolbar.searchView.visible()
            binding.toolbar.toolbarContainer.gone()
        }

        binding.toolbar.closeBtn.setOnClickListener {
            hideKeyBoard(requireActivity())
            binding.toolbar.txtSearch.text.clear()
            binding.toolbar.searchView.gone()
            binding.toolbar.toolbarContainer.visible()
        }
    }

    private fun initializeUI() {
        setToolbarItemsVisibility()
    }

    private fun setToolbarItemsVisibility() {
        binding.toolbar.search.visible()
        binding.toolbar.centerLogo.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.pref_icon)
    }

    private fun observeCoinsMarketsResource() {
        viewModel.coinsMarketsLiveData.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    binding.coinsProgress.visible()
                    binding.tableCoins.gone()
                    binding.coinsRecycler.gone()
                }
                Status.SUCCESS -> {
                    binding.coinsProgress.gone()
                    binding.tableCoins.visible()
                    binding.coinsRecycler.visible()
                }
                Status.ERROR -> {
                    binding.coinsProgress.gone()
                }
            }
        })
    }

    private fun searchCoinsMarkets(searchKey: String): LiveData<List<CurrencyItem>> {
        return this.viewModel.postSearchCoinsMarketsPage(searchKey)
    }

    private fun searchFullCoinsMarkets(): LiveData<List<CurrencyItem>> {
        return this.viewModel.postSearchFullCoinsMarketsPage()
    }

    private fun loadCoinsMarkets(page: Int) = this.viewModel.postCoinsMarketsPage(page)

    override fun onItemClick(currencyItem: CurrencyItem, view: View) {
        val bundle = bundleOf("currencyItem" to currencyItem)
        navController.navigate(R.id.coinDetailFragment, bundle)
    }

}