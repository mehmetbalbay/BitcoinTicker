package com.mehmetbalbay.bitcointicker.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun setImageWithGlide(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}