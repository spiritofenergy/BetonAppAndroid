package com.kodexgroup.betonapp.database.server.dao

import com.kodexgroup.betonapp.network.NetworkController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.Request
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserDAO {

    private val networkController = NetworkController()

    suspend fun login(login: String, password: String) : String {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("login", login)
            .addFormDataPart("password", password)
            .build()

        val request = Request.Builder()
            .url("https://api.seostor.ru/beton/test/users/auth.php")
            .post(body)
            .build()

        return networkController.execute(request)
    }

    suspend fun signUp(name: String, city: String, email: String, phone: String, login: String, password: String) : String {

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("name", name)
            .addFormDataPart("role", 0.toString())
            .addFormDataPart("city", city)
            .addFormDataPart("email", email)
            .addFormDataPart("phone", phone)
            .addFormDataPart("login", login)
            .addFormDataPart("password", password)
            .build()

        val request = Request.Builder()
            .url("https://api.seostor.ru/beton/test/users/signup.php")
            .post(requestBody)
            .build()

        return networkController.execute(request)
    }

}