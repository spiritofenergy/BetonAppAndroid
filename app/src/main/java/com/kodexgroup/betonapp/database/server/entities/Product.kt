package com.kodexgroup.betonapp.database.server.entities

import org.json.JSONObject
import java.sql.Date

class Product(
    val productId: String,
    val factory: Factory,
    val productName: String,
    val productDescription: String,
    val productRate: Double,
    val favoriteCount: Int,
    val productPrice: Double,
    val productSale: Int,
    val productPhotos: JSONObject,
    val productCountOrders: Int,
    val productType: Int,
    val dateAdded: Date
) {

    fun test() {

    }

}