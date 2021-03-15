package com.kodexgroup.betonapp.screens.app.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kodexgroup.betonapp.database.server.entities.Factory

class MainAppViewModel : ViewModel() {

    private var _factories: MutableLiveData<List<Factory>> = MutableLiveData()

    var factories: List<Factory>? = null
        get() = _factories.value
        set(value) {
            field = value

            _factories.value = value
        }

}