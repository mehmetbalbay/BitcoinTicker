package com.mehmetbalbay.bitcointicker.view.ui.coindetail

import android.app.Dialog
import android.content.Context
import android.os.*
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
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
import com.mehmetbalbay.bitcointicker.extension.visible
import com.mehmetbalbay.bitcointicker.extension.vm
import com.mehmetbalbay.bitcointicker.factory.AppViewModelFactory
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.entity.CurrentPrice
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.models.network.Description
import com.mehmetbalbay.bitcointicker.models.network.PriceChange24hInCurrency
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.utils.setImageWithGlide
import com.mehmetbalbay.bitcointicker.view.ui.auth.AuthViewModel
import com.mehmetbalbay.bitcointicker.view.ui.base.BaseBottomSheetFragment
import com.mehmetbalbay.bitcointicker.view.ui.mycoins.MyCoinsListener
import dagger.android.support.AndroidSupportInjection
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinDetailFragment : BaseBottomSheetFragment(), MyCoinsListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by lazy { vm(viewModelFactory, CoinDetailViewModel::class) }
    private val authViewModel by lazy { vm(viewModelFactory, AuthViewModel::class) }
    private lateinit var binding: FragmentCoinDetailBinding
    private lateinit var currencyItem: CurrencyItem
    private var coinDetailItem: CoinDetailItem? = null
    private var refreshIntervalTime: Long = 2000L

    private var isFavoriteCoin = false

    private var confirmIntervalTime: Long = 0L

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    companion object {
        private const val WHAT_MSG = 1
    }

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            loadCoinDetail(currencyItem)
        }
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
        binding.viewModel?.myCoinListener = this
        return binding.root
    }

    private fun initAndRefreshIntervalTime() {
        val refreshTime: String = refreshIntervalTime.toString()
        binding.refreshInterval.setText(refreshTime)
        binding.refreshInterval.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isNotEmpty()) {
                        confirmIntervalTime = it.trim().toString().toLong()
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAndRefreshIntervalTime()
        val bundle = arguments
        bundle?.let {
            currencyItem = it.get("currencyItem") as CurrencyItem
            loadCoinDetail(currencyItem)
            initializeToolbar(currencyItem)
        }
        setClickListeners()
        observeCoinDetailData()
    }

    private fun observeCoinDetailData() {
        viewModel.coinDetailLiveData.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    resource?.let {
                        setDetails(it.data)

                        coinDetailItem = it.data

                        it.data?.image?.small?.run {
                            toolbarCenterLogo(this)
                        }

                        it.data?.isFavorite?.run {

                        }
                    }

                    val msg = Message.obtain()
                    msg.what = WHAT_MSG
                    mHandler.sendMessageDelayed(msg, refreshIntervalTime)
                }
                Status.ERROR -> {

                }
            }
        })
    }

    private fun setClickListeners() {
        binding.confirmBtn.setOnClickListener {
            setIntervalTime(if (confirmIntervalTime != 0L) confirmIntervalTime else refreshIntervalTime)
        }
    }

    private fun setDetails(coinDetailItem: CoinDetailItem?) {
        coinDetailItem?.let {
            setDescription(it.description)
            setCurrentPrice(it.marketData?.currentPrice)
            setPriceChangePercentage24hIn(it.marketData?.priceChange24hInCurrency)
            setHashAlgorithm(it.hashingAlgorithm)
            setRefreshView()
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.description.text = Html.fromHtml(this.tr, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    binding.description.text = Html.fromHtml(this.tr)
                }

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
                val combineString = "$currentPriceStr $defaultCurrency"
                binding.currentPrice.text = combineString
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
            val combineString = "$priceChangePercentage24hInStr %"
            binding.priceChangePercentage24h.text = combineString
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
        binding.toolbar.add.setBackgroundResource(R.drawable.ic_baseline_add_24)
        currencyItem?.let {
            binding.toolbar.title.text = it.name
        }
        binding.toolbar.back.let {
            it.setOnClickListener { dialog?.dismiss() }
        }
        binding.toolbar.add.let {
            it.setOnClickListener {
                coinDetailItem?.run {
                    val firebaseUserData = authViewModel.user?.let { fireBaseUser ->
                        isFavoriteCoin = if (isFavoriteCoin) {
                            binding.toolbar.add.setBackgroundResource(R.drawable.ic_baseline_add_24)
                            false
                        } else {
                            binding.toolbar.add.setBackgroundResource(R.drawable.ic_baseline_remove_24)
                            true
                        }
                        viewModel.onAddFavoriteFireStore(fireBaseUser, this)
                    }
                }
            }
        }
    }

    private fun loadCoinDetail(currencyItem: CurrencyItem?) {
        currencyItem?.let {
            viewModel.postCoinDetailId(it.id)
        }
    }

    private fun setIntervalTime(changedTime: Long) {
        refreshIntervalTime = changedTime
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeMessages(WHAT_MSG)
    }

    private fun setRefreshView() {
        binding.refreshDateTxt.visible()
        binding.lastUpdatedLine.visible()
        binding.refreshDate.text = getCurrentDate()
    }

    private fun getCurrentDate(): String {
        val c: Date = Calendar.getInstance().time

        val df = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault())
        return df.format(c)
    }

    override fun onStarted() {
    }

    override fun onSuccess() {
    }

    override fun onFailure(message: String) {
    }
}