package com.kodexgroup.betonapp.screens.productlist

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.database.server.ServerController
import com.kodexgroup.betonapp.database.server.entities.Factory
import com.kodexgroup.betonapp.database.server.entities.Product
import com.kodexgroup.betonapp.screens.app.main.MainAppViewModel
import com.kodexgroup.betonapp.utils.FilterCode
import com.kodexgroup.betonapp.utils.views.FactoryListView
import com.kodexgroup.betonapp.utils.views.ProductListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class ProductListFragmentController(private val fragment: Fragment, private val context: Context, view: View) {

    private val products: ProductListView = view.findViewById(R.id.product_list_view)

    private val viewModel: ProductListViewModel = ViewModelProvider(fragment).get(ProductListViewModel::class.java)

    private var listProduct: List<Product>? = viewModel.products

    init {
        products.emptyView.setOnActionListener {
            CoroutineScope(Dispatchers.IO).launch {
                reloadList()
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (listProduct == null) load()
            setList()
        }
    }

    suspend fun load() {
        val types = fragment.arguments?.getIntegerArrayList("type")

        CoroutineScope(Dispatchers.Main).launch {
            products.isLoad = true
        }

        try {
            val productDAO = ServerController().productDAO
            val list = productDAO.getProducts(type = types)

            listProduct = filtersList(list)
        } catch (e: IOException) {
            products.emptyInternetLost()
        }
    }

    private fun filtersList(list: List<Product>) : List<Product> {
        var newList: List<Product> = list

        val filters = fragment.arguments?.getIntegerArrayList("filter")
        filters?.apply {
            for (filter in this) {

                when (filter) {
                    FilterCode.NEAR -> {

                    }
                    FilterCode.SALE -> {
                        newList = newList.filter { it.productSale != 0 }
                    }
                    FilterCode.NEW -> {

                    }

                    FilterCode.RATING_2 -> {

                    }
                    FilterCode.RATING_3-> {

                    }
                    FilterCode.RATING_4 -> {

                    }
                    FilterCode.RATING_5 -> {

                    }
                }

            }
        }

        return newList
    }

    private suspend fun setList() {
        withContext(Dispatchers.Main) {
            listProduct?.apply {
                viewModel.products = this

                if (isNotEmpty()) {
                    products.showList(this)
                } else {
                    products.isEmpty = true
                }
            }
        }
    }

    suspend fun reloadList() {
        load()
        setList()
    }

}