package com.mehmetbalbay.bitcointicker.view.ui.prefscreen

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelActivity
import com.mehmetbalbay.bitcointicker.databinding.ActivityPrefScreenBinding
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.utils.startMainActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class PrefScreenActivity : ViewModelActivity(), HasAndroidInjector {

    var listOfLanguages = arrayOf("English", "Turkish")
    var listOfCurrency = arrayOf("BTC", "ETH", "USD", "TRY")

    var selectedLanguage: String? = null
    var selectedCurrency: String? = null

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>
    private val viewModel: PrefScreenViewModel by injectViewModels()
    private val binding: ActivityPrefScreenBinding by binding(R.layout.activity_pref_screen)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeUI()
        setListeners()
    }

    private fun initializeUI() {
        initLanguageSpinner()
        initCurrencySpinner()
    }

    private fun setListeners() {

        binding.start.setOnClickListener {
            if (!selectedLanguage.isNullOrEmpty() && !selectedCurrency.isNullOrEmpty()) {
                SharedPreferenceHelper.saveSharedData(Const.DEFAULT_LANGUAGE, selectedLanguage)
                SharedPreferenceHelper.saveSharedData(Const.DEFAULT_CURRENCY, selectedCurrency)
                startMainActivity()
                this.finish()
            }
        }

        binding.defaultLanguageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedLanguage = parent?.selectedItem as String
                }
            }

        binding.defaultCurrencySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedCurrency = parent?.selectedItem as String
                }
            }
    }

    private fun initLanguageSpinner() {
        val arrayAdapter = ArrayAdapter(this, R.layout.item_custom_spinner, listOfLanguages)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.defaultLanguageSpinner.adapter = arrayAdapter
    }

    private fun initCurrencySpinner() {
        val arrayAdapter = ArrayAdapter(this, R.layout.item_custom_spinner, listOfCurrency)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.defaultCurrencySpinner.adapter = arrayAdapter
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}