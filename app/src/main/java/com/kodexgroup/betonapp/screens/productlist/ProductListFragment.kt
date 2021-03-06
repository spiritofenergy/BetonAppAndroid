package com.kodexgroup.betonapp.screens.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.screens.SecondFragment
import com.kodexgroup.betonapp.utils.views.ButtonTypeView
import com.kodexgroup.betonapp.utils.views.FiltersView
import com.kodexgroup.betonapp.utils.views.ProductListView

class ProductListFragment : SecondFragment() {

    private lateinit var productList: ProductListView
    private lateinit var btns: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = super.onCreateView(inflater, container, savedInstanceState)

        addView(LayoutInflater.from(context).inflate(R.layout.content_product_list, null))

        productList = root.findViewById(R.id.product_list_view)
        btns = root.findViewById(R.id.types_product)

        return root
    }

    fun onReloadedState() {
        println(arguments)
        productList.load()
        for (btn in btns.children) {
            if (btn is ButtonTypeView) {
                btn.reloadAlpha()
            }
        }
    }

}