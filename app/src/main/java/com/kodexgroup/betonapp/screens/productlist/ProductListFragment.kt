package com.kodexgroup.betonapp.screens.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.views.ButtonTypeView
import com.kodexgroup.betonapp.utils.views.ProductListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {

    private lateinit var productList: ProductListView
    private lateinit var btns: LinearLayout

    private lateinit var controller: ProductListFragmentController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.content_product_list, container, false)

        controller = ProductListFragmentController(this, requireContext(), root)

        productList = root.findViewById(R.id.product_list_view)
        btns = root.findViewById(R.id.types_product)

        return root
    }

    fun onReloadedState() {
        println(arguments)

        CoroutineScope(Dispatchers.IO).launch {
            controller.reloadList()
        }

        for (btn in btns.children) {
            if (btn is ButtonTypeView) {
                btn.reloadAlpha()
            }
        }
    }

}