package com.kodexgroup.betonapp.database.server.entities

import com.kodexgroup.betonapp.utils.xml.interfaces.IXMLClass

class UserEntity : IXMLClass {

    private lateinit var _id: String
    val id: String
        get() = _id

    private lateinit var _name: String
    val name: String
        get() = _name

    private lateinit var _login: String
    val login: String
        get() = _login

    companion object {
        const val ID = "Id"
        const val NAME = "Name"
        const val LOGIN = "Login"
    }

    override fun setData(key: String, value: String) {
        when (key) {
            ID -> {
                _id = value
            }
            NAME -> {
                _name = value
            }
            LOGIN -> {
                _login = value
            }
        }
    }

}