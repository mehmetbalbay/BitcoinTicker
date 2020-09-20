package com.mehmetbalbay.bitcointicker.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mehmetbalbay.bitcointicker.models.Resource
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem

@BindingAdapter("hashAlgorithm")
fun bindHashAlgorithm(view: TextView, resource: Resource<CoinDetailItem>?) {
    resource?.data?.let {
        it.hashingAlgorithm?.run {
            val hashAlgorithm = "Hashing Algorithm: $this"
            view.text = hashAlgorithm
        }
    }
}