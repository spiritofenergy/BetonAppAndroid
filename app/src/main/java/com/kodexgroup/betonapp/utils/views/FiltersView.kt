package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.google.android.flexbox.FlexboxLayout
import com.kodexgroup.betonapp.R

class FiltersView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val filterList: FlexboxLayout

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.view_filters, this, true)

        filterList = root.findViewById(R.id.filters_list)

        addButtons(listOf("Button", "Btn", "Oopen"))
    }

    fun addBtn(textTitle: String) {
        val button = ButtonFilterView(context, null).apply {
            isActive = true
            text = textTitle
            isInFilterView = true
        }
        filterList.addView(button)
    }

    fun addButtons(list: List<String>) {
        for (text in list) {
            addBtn(text)
        }
    }

}