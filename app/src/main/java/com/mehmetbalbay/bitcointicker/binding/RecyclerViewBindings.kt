package com.mehmetbalbay.bitcointicker.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.view.adapter.coinsmarkets.CoinsMarketsAdapter
import com.mehmetbalbay.bitcointicker.view.adapter.mycoins.MyCoinsAdapter
import com.skydoves.baserecyclerviewadapter.BaseAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
    view.adapter = baseAdapter
}

@BindingAdapter("adapterCoinsMarkets")
fun bindAdapterCoinsMarkets(view: RecyclerView, resource: Resource<List<CurrencyItem>>?) {
    if (resource != null) {
        val adapter = view.adapter as? CoinsMarketsAdapter
        adapter?.addCurrencyItemList(resource)
    }
}

@BindingAdapter("adapterMyCoins")
fun bindAdapterMyCoins(view: RecyclerView, resource: Resource<List<CoinDetailItem>>?) {
    if (resource != null) {
        val adapter = view.adapter as? MyCoinsAdapter
        adapter?.addCurrencyItemList(resource)
    }
}