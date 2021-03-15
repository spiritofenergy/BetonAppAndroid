package com.kodexgroup.betonapp.screens.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kodexgroup.betonapp.database.server.entities.Factory
import com.kodexgroup.betonapp.database.server.entities.Product

class ProductListViewModel : ViewModel() {

    private var _products: MutableLiveData<List<Product>> = MutableLiveData()

    var products: List<Product>? = null
        get() = _products.value
        set(value) {
            field = value

            _products.value = value
        }

}