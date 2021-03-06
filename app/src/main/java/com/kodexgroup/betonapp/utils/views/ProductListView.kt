package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.database.server.ServerController
import com.kodexgroup.betonapp.database.server.entities.Factory
import com.kodexgroup.betonapp.database.server.entities.Product
import com.kodexgroup.betonapp.screens.productlist.ProductListFragment
import com.kodexgroup.betonapp.utils.FilterCode
import com.kodexgroup.betonapp.utils.adapters.ProductListAdapter
import com.kodexgroup.betonapp.utils.findParentNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.*


class ProductListView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val listProduct: RecyclerView
    private val loaded: ProgressBar
    private val emptyView: EmptyView

    private var fragment: ProductListFragment? = null
    private val adapter =  ProductListAdapter()

    private  var isEmpty: Boolean = false
        set(value) {
            field = value

            if (value) {
                loaded.visibility = GONE
                listProduct.visibility = GONE
                emptyView.visibility = VISIBLE
            } else {
                loaded.visibility = GONE
                listProduct.visibility = VISIBLE
                emptyView.visibility = GONE
            }
        }

    init {
        val root = inflate(context, R.layout.view_product_list, this)

        listProduct = root.findViewById(R.id.list_product)
        loaded = root.findViewById(R.id.loaded_product_list)
        emptyView = root.findViewById(R.id.empty_product_list)

        listProduct.layoutManager = LinearLayoutManager(context)

        emptyView.setOnActionListener {
            load()
        }

        listProduct.adapter = adapter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!isInEditMode) {
            try {
                fragment = findFragment()
            } catch (e: ClassCastException) { }
            load()
        }
    }

    fun load() {
        val types = fragment?.arguments?.getIntegerArrayList("type")

        emptyView.visibility = GONE
        loaded.visibility = VISIBLE
        listProduct.visibility = GONE

        CoroutineScope(Dispatchers.IO).launch {


            val productDAO = ServerController().productDAO
            var list = productDAO.getProducts(type = types)

            list = filtersList(list)
            if (list.isNotEmpty()) {
                showList(list)
            } else {
                withContext(Dispatchers.Main) {
                    isEmpty = true
                }
            }
        }
    }

    private fun filtersList(list: List<Product>) : List<Product> {
        var newList: List<Product> = list

        val filters = fragment?.arguments?.getIntegerArrayList("filter")
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

    private suspend fun showList(list: List<Product>) {
        withContext(Dispatchers.Main) {
            val newList = mutableListOf<Pair<Product, Product?>>()

            val iterator = list.iterator()
            iterator.forEach { product ->
                val next = if (iterator.hasNext()) iterator.next() else null
                val pair = Pair(product, next)

                newList.add(pair)
            }
            println(newList)
            adapter.reload(newList)

            loaded.visibility = GONE
            listProduct.visibility = VISIBLE
        }
    }
}