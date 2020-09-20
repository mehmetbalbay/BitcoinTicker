package com.mehmetbalbay.bitcointicker.view.ui.viewholder.coinsmarkets

import android.view.View
import com.mehmetbalbay.bitcointicker.databinding.ItemCoinBinding
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.view.ui.viewholder.bindings
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

class CoinsMarketsViewHolder(
    view: View,
    private val delegate: Delegate
) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(currentItem: CurrencyItem, view: View)
    }

    private lateinit var currentItem: CurrencyItem
    private val binding by bindings<ItemCoinBinding>(view)

    override fun bindData(data: Any) {
        if (data is CurrencyItem) {
            currentItem = data

            binding.apply {
                currentItem = data
                executePendingBindings()
            }
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            delegate.onItemClick(currentItem, view)
        }
    }

    override fun onLongClick(v: View?): Boolean = false

}