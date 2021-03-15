package com.kodexgroup.betonapp.screens.factory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kodexgroup.betonapp.database.server.entities.Factory

class FactoryViewModel : ViewModel() {

    private var _factory: MutableLiveData<Factory> = MutableLiveData()

    var factory: Factory? = null
        get() = _factory.value
        set(value) {
            field = value

            _factory.value = value
        }

}