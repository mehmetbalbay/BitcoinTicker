package com.mehmetbalbay.bitcointicker.view.ui.coindetail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.databinding.FragmentCoinDetailBinding
import com.mehmetbalbay.bitcointicker.enums.Status
import com.mehmetbalbay.bitcointicker.extension.gone
import com.mehmetbalbay.bitcointicker.extension.visible
import com.mehmetbalbay.bitcointicker.extension.vm
import com.mehmetbalbay.bitcointicker.factory.AppViewModelFactory
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.models.network.*
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.utils.setImageWithGlide
import com.mehmetbalbay.bitcointicker.view.ui.base.BaseBottomSheetFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CoinDetailFragment : BaseBottomSheetFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by lazy { vm(viewModelFactory, CoinDetailViewModel::class) }
    private lateinit var binding: FragmentCoinDetailBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet =
                (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
            setupFullHeight(it)
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coin_detail, container, false)
        binding.apply {
            viewModel = this@CoinDetailFragment.viewModel
            lifecycleOwner = this@CoinDetailFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let {
            val currencyItem = it.get("currencyItem") as CurrencyItem
            loadCoinDetail(currencyItem)
            initializeToolbar(currencyItem)
        }

        viewModel.coinDetailLiveData.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    binding.detailProgress.visible()
                    binding.detailContainer.gone()
                }
                Status.SUCCESS -> {
                    resource?.let {
                        setDetails(it.data)
                        it.data?.image?.small?.run {
                            toolbarCenterLogo(this)
                        }
                    }
                    binding.detailProgress.gone()
                    binding.detailContainer.visible()
                }
                Status.ERROR -> {
                    binding.detailProgress.gone()
                }
            }
        })
    }

    private fun setDetails(coinDetailItem: CoinDetailItem?) {
        coinDetailItem?.let {
            setDescription(it.description)
            setCurrentPrice(it.marketData.currentPrice)
            setPriceChangePercentage24hIn(it.marketData.priceChange24hInCurrency)
            setHashAlgorithm(it.hashingAlgorithm)
        }
    }

    private fun setHashAlgorithm(hashAlgorithm: String?) {
        hashAlgorithm?.run {
            if (this.isNotEmpty()) {
                binding.hashAlgorithm.text = this
                binding.hashAlgorithmTxt.visible()
            }
        }
    }

    private fun setDescription(description: Description?) {
        description?.run {
            if (this.tr.isNotEmpty()) {
                binding.description.text = this.tr
                binding.descriptionTxt.visible()
                binding.descriptionLine.visible()
            }
        }
    }

    private fun setCurrentPrice(currentPrice: CurrentPrice?) {
        currentPrice?.run {
            val defaultCurrency =
                SharedPreferenceHelper.getSharedData(Const.DEFAULT_CURRENCY) as? String
            if (!defaultCurrency.isNullOrEmpty()) {
                val currentPriceStr: String = when (defaultCurrency) {
                    "TRY" -> {
                        this.tryX.toString()
                    }
                    "USD" -> {
                        this.usd.toString()
                    }
                    "ETH" -> {
                        this.eth.toString()
                    }
                    else -> {
                        this.btc.toString()
                    }
                }
                binding.currentPrice.text = "$currentPriceStr $defaultCurrency"
                binding.currentPriceTxt.visible()
                binding.currentPriceLine.visible()
            }
        }
    }

    private fun setPriceChangePercentage24hIn(priceChange24hInCurrency: PriceChange24hInCurrency?) {
        priceChange24hInCurrency?.run {
            var priceChangePercentage24hInStr = ""
            priceChangePercentage24hInStr = when (priceChangePercentage24hInStr) {
                "TRY" -> {
                    this.tryX.toString()
                }
                "USD" -> {
                    this.usd.toString()
                }
                "ETH" -> {
                    this.eth.toString()
                }
                else -> {
                    this.btc.toString()
                }
            }
            binding.priceChangePercentage24h.text = "$priceChangePercentage24hInStr %"
            binding.priceChangePercentage24hTxt.visible()
            binding.priceChangePercentage24hLine.visible()
        }
    }

    private fun toolbarCenterLogo(imgUrl: String) {
        binding.toolbar.centerLogo.let {
            setImageWithGlide(it, imgUrl)
        }
    }

    private fun initializeToolbar(currencyItem: CurrencyItem?) {
        binding.toolbar.back.visible()
        binding.toolbar.add.visible()
        currencyItem?.let {
            binding.toolbar.title.text = it.name
        }
        binding.toolbar.back.let {
            it.setOnClickListener { dialog?.dismiss() }
        }
    }

    private fun loadCoinDetail(currencyItem: CurrencyItem?) {
        currencyItem?.let {
            viewModel.postCoinDetailId(it)
        }
    }
}