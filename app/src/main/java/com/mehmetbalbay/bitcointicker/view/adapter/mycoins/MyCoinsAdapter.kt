package com.mehmetbalbay.bitcointicker.view.adapter.mycoins

import android.view.View
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.view.ui.viewholder.mycoins.MyCoinsViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class MyCoinsAdapter(
    private val delegate: MyCoinsViewHolder.Delegate
) : BaseAdapter() {

    init {
        addSection(ArrayList<CoinDetailItem>())
    }

    fun addCurrencyItemList(resource: Resource<List<CoinDetailItem>?>) {
        resource.data?.let {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    fun setData(currencyList: List<CoinDetailItem>?) {
        currencyList?.let {
            sections()[0].clear()
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int = R.layout.item_my_coins

    override fun viewHolder(layout: Int, view: View): BaseViewHolder =
        MyCoinsViewHolder(view, delegate)

}