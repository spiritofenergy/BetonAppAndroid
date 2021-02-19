package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.kodexgroup.betonapp.R

class MiniCardProductView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.content_mini_card_product, this, true)
    }

}