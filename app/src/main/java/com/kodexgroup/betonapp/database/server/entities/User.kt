package com.kodexgroup.betonapp.database.server.entities

import com.kodexgroup.betonapp.utils.json.JSON
import com.kodexgroup.betonapp.utils.xml.interfaces.IXMLClass
import org.json.JSONObject

class User : IXMLClass {

    private lateinit var _id: String
    val id: String
        get() = _id

    private lateinit var _name: String
    val name: String
        get() = _name

    private lateinit var _login: String
    val login: String
        get() = _login

    private var _favorites: JSONObject? = null
    var favorites: JSONObject? = null
        get() = _favorites

    companion object {
        const val START_TAG = "User"
        const val ID = "Id"
        const val NAME = "Name"
        const val LOGIN = "Login"
        const val FAVORITES = "Favorites"
    }

    override fun setData(key: String, value: String?) {
        val newValue = value ?: ""
        when (key) {
            ID -> {
                _id = newValue
            }
            NAME -> {
                _name = newValue
            }
            LOGIN -> {
                _login = newValue
            }
            FAVORITES -> {
                if (newValue != "")
                    _favorites = JSON.Response(newValue)
            }
        }
    }

}