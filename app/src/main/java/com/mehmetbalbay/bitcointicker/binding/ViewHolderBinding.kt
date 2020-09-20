package com.mehmetbalbay.bitcointicker.binding

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.helper.SharedPreferenceHelper
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.view.GlideApp

@BindingAdapter("bindingCoinImageUrl")
fun bindingCoinImageUrl(imageView: ImageView, path: String?) {
    path?.let {
        GlideApp.with(imageView.context)
            .load(it)
            .error(ContextCompat.getDrawable(imageView.context, R.drawable.pref_icon))
            .into(imageView)
    }
}

@BindingAdapter("bindingFloatToText")
fun bindingFloatToText(textView: TextView, value: Float) {
    textView.text = value.toString()
}

@BindingAdapter("bindingPriceChangePercentage24hToText")
fun bindingPriceChangePercentage24hToText(textView: TextView, value: Float) {
    val price24HPercentageValue: String = value.toString()
    if (price24HPercentageValue.contains("-")) {
        textView.setTextColor(Color.RED)
    } else {
        textView.setTextColor(Color.GREEN)
    }
    val combineString = "$value %"
    textView.text = combineString
}

@BindingAdapter("bindingMarketCapToText")
fun bindingMarketCapToText(textView: TextView, value: Float) {
    val castValue = value.toString()
    var combineString = ""
    val currency = SharedPreferenceHelper.getSharedData(Const.DEFAULT_CURRENCY) as String?
    currency?.let {
        combineString = if (castValue.length >= 6) {
            "${castValue.substring(0, 6)} $it"

        } else {
            "$castValue $it"
        }
    }
    textView.text = combineString
}

@BindingAdapter("bindingIntToText")
fun bindingIntToText(textView: TextView, value: Int) {
    textView.text = value.toString()
}