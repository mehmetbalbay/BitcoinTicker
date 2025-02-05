package com.mehmetbalbay.bitcointicker.view.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int, setOnClickListener: (model: T, position: Int) -> Unit)
}