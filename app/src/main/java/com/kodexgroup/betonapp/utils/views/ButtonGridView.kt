package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.kodexgroup.betonapp.R

class ButtonGridView(context: Context, attr: AttributeSet?) : LinearLayout(context, attr) {

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.content_search_item_grid_item, this, true)
    }

}