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
    val emptyView: EmptyView

    private val adapter =  ProductListAdapter()

    var isEmpty: Boolean = false
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

    var isLoad: Boolean = true
        set(value) {
            field = value

            if (value) {
                emptyView.visibility = GONE
                loaded.visibility = VISIBLE
                listProduct.visibility = GONE
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

        listProduct.adapter = adapter
    }

    fun showList(list: List<Product>) {
        val newList = mutableListOf<Pair<Product, Product?>>()

        val iterator = list.iterator()
        iterator.forEach { product ->
            val next = if (iterator.hasNext()) iterator.next() else null
            val pair = Pair(product, next)

            newList.add(pair)
        }
        println(newList)
        adapter.reload(newList)

        isLoad = false

    }

    fun emptyInternetLost() {
        emptyView.title = "Отсутствует подключение к интернету."
        emptyView.subText = "Проверьте ваше подключение или обновите список."

        isEmpty = true
    }
}