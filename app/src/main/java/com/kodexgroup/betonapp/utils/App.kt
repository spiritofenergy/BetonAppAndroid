package com.kodexgroup.betonapp.utils

import android.app.Application
import com.kodexgroup.betonapp.database.server.entities.User

class App : Application() {

    var currentUser: User? = null

    override fun onCreate() {
        super.onCreate()


    }

}