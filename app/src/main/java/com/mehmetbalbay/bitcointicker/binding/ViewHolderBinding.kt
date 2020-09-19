package com.mehmetbalbay.bitcointicker.binding

import android.annotation.SuppressLint
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

@SuppressLint("SetTextI18n")
@BindingAdapter("bindingMarketCapToText")
fun bindingMarketCapToText(textView: TextView, value: Float) {
    val castValue = value.toString()
    val currency = SharedPreferenceHelper.getSharedData(Const.DEFAULT_CURRENCY) as String?
    currency?.let {
        textView.text = "$castValue $it"
    }
}

@BindingAdapter("bindingIntToText")
fun bindingIntToText(textView: TextView, value: Int) {
    textView.text = value.toString()
}