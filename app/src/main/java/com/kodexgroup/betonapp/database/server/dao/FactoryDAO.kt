package com.kodexgroup.betonapp.database.server.dao

import com.kodexgroup.betonapp.database.server.entities.Factory
import com.kodexgroup.betonapp.database.server.entities.Product
import com.kodexgroup.betonapp.network.NetworkController
import com.kodexgroup.betonapp.utils.xml.XMLParser
import okhttp3.MultipartBody
import okhttp3.Request

class FactoryDAO {

    private val networkController = NetworkController()

    suspend fun getFactory(id: String? = null, user: String? = null) : List<Factory> {
        val bodyBuilder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
        val request = Request.Builder()
                .url("https://api.seostor.ru/beton/test/factories/get.php")

        if (id != null || user != null) {
            bodyBuilder.apply {
                if (id != null) {
                    addFormDataPart("factory", id)
                }
                if (user != null) {
                    addFormDataPart("user", user)
                }
            }
            val body = bodyBuilder.build()
            request.post(body)
        }

        val xml = networkController.execute(request.build())

        val xmlParser = XMLParser()
        val parser = xmlParser.getParser(xml)

        return xmlParser.getObjectFromParser(parser, Factory.START_TAG)
    }

}