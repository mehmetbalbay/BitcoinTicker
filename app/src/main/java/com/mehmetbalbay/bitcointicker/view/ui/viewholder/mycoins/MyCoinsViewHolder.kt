package com.mehmetbalbay.bitcointicker.view.ui.viewholder.mycoins

import android.view.View
import com.mehmetbalbay.bitcointicker.databinding.ItemMyCoinsBinding
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.view.ui.viewholder.bindings
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

class MyCoinsViewHolder(
    view: View,
    private val delegate: Delegate
) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(coinDetailItem: CoinDetailItem, view: View)
    }

    private lateinit var coinDetailItem: CoinDetailItem
    private val binding by bindings<ItemMyCoinsBinding>(view)

    override fun bindData(data: Any) {
        if (data is CoinDetailItem) {
            coinDetailItem = data

            binding.apply {
                coinDetailItem = data
                executePendingBindings()
            }
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            delegate.onItemClick(coinDetailItem, view)
        }
    }

    override fun onLongClick(v: View?): Boolean = false

}