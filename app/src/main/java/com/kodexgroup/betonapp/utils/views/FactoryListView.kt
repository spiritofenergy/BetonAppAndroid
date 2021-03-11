package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.database.server.ServerController
import com.kodexgroup.betonapp.database.server.entities.Factory
import com.kodexgroup.betonapp.database.server.entities.Product
import com.kodexgroup.betonapp.screens.productlist.ProductListFragment
import com.kodexgroup.betonapp.utils.adapters.FactoryListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FactoryListView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val listFactory: RecyclerView
    private val loaded: ProgressBar
    private val emptyView: EmptyView

    private var fragment: ProductListFragment? = null
    private val adapter =  FactoryListAdapter(context)

    private  var isEmpty: Boolean = false
        set(value) {
            field = value

            if (value) {
                loaded.visibility = GONE
                listFactory.visibility = GONE
                emptyView.visibility = VISIBLE
            } else {
                loaded.visibility = GONE
                listFactory.visibility = VISIBLE
                emptyView.visibility = GONE
            }
        }

    init {
        val root = inflate(context, R.layout.view_factory_list, this)

        listFactory = root.findViewById(R.id.factory_list)
        loaded = root.findViewById(R.id.loaded_factory_list)
        emptyView = root.findViewById(R.id.empty_factory_list)

        listFactory.layoutManager = LinearLayoutManager(context)

        emptyView.setOnActionListener {
            load()
        }

        listFactory.adapter = adapter
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
        emptyView.visibility = GONE
        loaded.visibility = VISIBLE
        listFactory.visibility = GONE

        CoroutineScope(Dispatchers.IO).launch {


            val factoryDAO = ServerController().factoryDAO
            val list = factoryDAO.getFactory()

            if (list.isNotEmpty()) {
                showList(list.reversed())
            } else {
                withContext(Dispatchers.Main) {
                    isEmpty = true
                }
            }
        }
    }

    private suspend fun showList(list: List<Factory>) {
        withContext(Dispatchers.Main) {
            adapter.reload(list)

            loaded.visibility = GONE
            listFactory.visibility = VISIBLE
        }
    }
}