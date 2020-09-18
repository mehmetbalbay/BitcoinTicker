package com.mehmetbalbay.bitcointicker.view.ui.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.mehmetbalbay.bitcointicker.utils.AppFonts

class TextViewWithFontBold : AppCompatTextView {
    constructor(context: Context) : super(context) {
        this.typeface = AppFonts.MontserratBold
    }

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    ) {
        this.typeface = AppFonts.MontserratBold
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        this.typeface = AppFonts.MontserratBold
    }
}