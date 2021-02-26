package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import com.google.android.flexbox.FlexboxLayout
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.screens.productlist.ProductListFragment
import com.kodexgroup.betonapp.utils.FilterCode
import com.kodexgroup.betonapp.utils.dialogs.FiltersDialog
import com.kodexgroup.betonapp.utils.dialogs.SearchFormDialog
import com.kodexgroup.betonapp.utils.getFragmentManager

class FiltersView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val filterList: FlexboxLayout
    private val openDialog: ImageButton
    private val empty: TextView

    private var fm: FragmentManager? = null
    private var fragment: ProductListFragment? = null

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.view_filters, this, true)

        filterList = root.findViewById(R.id.filters_list)
        openDialog = root.findViewById(R.id.open_filters)
        empty = root.findViewById(R.id.empty_filters)

        if (!isInEditMode) {
            fm = getFragmentManager(context)
        }

        openDialog.setOnClickListener {
            val dialog = FiltersDialog()
//            dialog.arguments
            fm?.let { dialog.show(it, "") }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isInEditMode) {
            try {
                fragment = findFragment()
            } catch (e: ClassCastException) { }

            when (fragment?.arguments?.getInt("filter", -1)) {
                FilterCode.SALE -> addBtn("Скидка")
                FilterCode.NEW -> addBtn("Новые")
                else -> { empty.visibility = VISIBLE }
            }

        }
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