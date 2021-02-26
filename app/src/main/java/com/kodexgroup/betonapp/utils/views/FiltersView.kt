package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.os.Bundle
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
import com.kodexgroup.betonapp.utils.getFragmentManager
import java.util.ArrayList

class FiltersView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val filterList: FlexboxLayout
    private val openDialog: ImageButton
    private val empty: TextView

    private var fm: FragmentManager? = null
    private var fragment: ProductListFragment? = null

    private var filters: ArrayList<Int> = arrayListOf()

    private val onDismiss: (ArrayList<Int>) -> Unit = {
        println(filters)
        for (code in it) {
            if (code !in filters) {
                filters.add(code)
            }
        }
        reloadButtons()
        if (it.isEmpty()) reload()
    }

    private var isEmpty: Boolean = true
        set(value) {
            field = value

            if (value) {
                empty.visibility = VISIBLE
            } else {
                empty.visibility = GONE
            }
        }

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.view_filters, this, true)

        filterList = root.findViewById(R.id.filters_list)
        openDialog = root.findViewById(R.id.open_filters)
        empty = root.findViewById(R.id.empty_filters)

        openDialog.setOnClickListener {
            val dialog = FiltersDialog()
            dialog.setOnDismissListener {
                onDismiss(it)
            }
            val args = Bundle()
            args.putIntegerArrayList("filters", filters)

            dialog.arguments = args
            fm?.let { dialog.show(it, "") }
        }

        empty.setOnClickListener {
            val dialog = FiltersDialog()
            dialog.setOnDismissListener {
                onDismiss(it)
            }

            fm?.let { dialog.show(it, "") }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isInEditMode) {
            fm = getFragmentManager(context)

            try {
                fragment = findFragment()
            } catch (e: ClassCastException) { }

            filters = fragment?.arguments?.getIntegerArrayList("filter") ?: arrayListOf()

            reloadButtons()

            if (filters.isEmpty()) reload()
        }
    }

    private fun addBtn(textTitle: String, tag: Int) {
        val button = ButtonFilterView(context, null).apply {
            isActive = true
            text = textTitle
            isInFilterView = true
        }
        button.tag = tag
        filterList.addView(button)
        reload()
    }

    private fun reloadButtons() {
        filterList.removeAllViews()
        for (filter in filters) {

            when (filter) {
                FilterCode.NEAR -> addBtn("Рядом", FilterCode.NEAR)
                FilterCode.SALE -> addBtn("Скидка", FilterCode.SALE)
                FilterCode.NEW -> addBtn("Новые", FilterCode.NEW)
            }

        }
    }

    fun deleteBtn(v: ButtonFilterView) {
        filters.remove(v.tag)
        filterList.removeView(v)
        reload()
    }

    private fun reload() {
        isEmpty = filterList.childCount == 0
    }

}