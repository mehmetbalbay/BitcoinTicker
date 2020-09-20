package com.mehmetbalbay.bitcointicker.view.adapter.coinsmarkets

import android.view.View
import androidx.core.content.ContextCompat
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.network.CurrencyItem
import com.mehmetbalbay.bitcointicker.view.ui.viewholder.coinsmarkets.CoinsMarketsViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinsMarketsAdapter(
    private val delegate: CoinsMarketsViewHolder.Delegate
) : BaseAdapter() {

    init {
        addSection(ArrayList<CurrencyItem>())
    }

    fun addCurrencyItemList(resource: Resource<List<CurrencyItem>?>) {
        resource.data?.let {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    fun setData(currencyList: List<CurrencyItem>?) {
        currencyList?.let {
            sections()[0].clear()
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int = R.layout.item_coin

    override fun viewHolder(layout: Int, view: View): BaseViewHolder =
        CoinsMarketsViewHolder(view, delegate)

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        val colorPos = position % 2
        if (colorPos == 0) {
            viewHolder.itemView.tableCoins.setBackgroundColor(
                ContextCompat.getColor(
                    viewHolder.itemView.context,
                    R.color.colorPrimaryDark
                )
            )
        } else {
            viewHolder.itemView.tableCoins.setBackgroundColor(
                ContextCompat.getColor(
                    viewHolder.itemView.context,
                    R.color.colorPrimary
                )
            )
        }
    }

}