package com.kodexgroup.betonapp.database.server

import com.kodexgroup.betonapp.database.server.dao.UserDAO

class ServerController {
    val userDAO: UserDAO = UserDAO()
}