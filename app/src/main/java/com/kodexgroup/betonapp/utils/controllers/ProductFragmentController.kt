package com.kodexgroup.betonapp.utils.controllers

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.database.server.ServerController
import com.kodexgroup.betonapp.database.server.entities.Factory
import com.kodexgroup.betonapp.database.server.entities.Product
import com.kodexgroup.betonapp.utils.views.MiniCardProductView
import com.kodexgroup.betonapp.utils.views.RatingView
import com.kodexgroup.betonapp.utils.views.ReviewBlockView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductFragmentController(private val context: Context, view: View, productId: String) {

    private val previewProduct: ImageView = view.findViewById(R.id.image_main_product)
    private val title: TextView = view.findViewById(R.id.title_product)
    private val factoryBlock: LinearLayout = view.findViewById(R.id.factory_block)
    private val titleFactory: TextView = view.findViewById(R.id.factory_title_product)
    private val factoryBtn: TextView = view.findViewById(R.id.to_factory_form_product)
    private val ratingView: RatingView = view.findViewById(R.id.rating_product)
    private val noteBtn: ImageButton = view.findViewById(R.id.add_favorite_product)
    private val favoriteCountTxt: TextView = view.findViewById(R.id.favorite_count_product)
    private val description: TextView = view.findViewById(R.id.description_product)
    private val priceBlock: LinearLayout = view.findViewById(R.id.price_block)
    private val price: TextView = view.findViewById(R.id.price_main_product)
    private val salePrice: TextView = view.findViewById(R.id.price_sale_product)
    private val addToBucket: Button = view.findViewById(R.id.to_busket_product)
    private val reviewBlock: ReviewBlockView = view.findViewById(R.id.reviews_product)
    private val cardFirst: MiniCardProductView = view.findViewById(R.id.first_card)
    private val cardSecond: MiniCardProductView = view.findViewById(R.id.second_card)

    private var product: Product? = null
    private var factory: Factory? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getProduct(productId)
            setProduct()
        }
    }

    private suspend fun getProduct(id: String) {
        val dao = ServerController().productDAO

        val products = dao.getProducts(listOf(id))
        if (products.isNotEmpty()) {
            product = products.first()
        }
    }

    private suspend fun setProduct() {
        withContext(Dispatchers.Main) {

            product?.apply {
                getFactory(factory)
                getLiked(productType)

                previewProduct.setImageResource(R.drawable.concrete)

                title.text = productName
                title.background = null
                val param = title.layoutParams
                param.width = ViewGroup.LayoutParams.WRAP_CONTENT

                title.layoutParams = param

                ratingView.rating = productRate.toFloat()
                favoriteCountTxt.text = favoriteCount.toString()
                description.text = productDescription
                description.background = null

                var priceTotal = productPrice
                if (productSale != 0) {
                    salePrice.text = context.getString(R.string.sale_price_strike, priceTotal)
                    salePrice.paintFlags = salePrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    salePrice.visibility = View.VISIBLE

                    priceTotal -= (priceTotal * productSale / 100)
                    price.text = context.getString(R.string.price_mini_card, priceTotal)
                } else {
                    price.text = context.getString(R.string.price_mini_card, priceTotal)
                }
                priceBlock.background = null
                price.visibility = View.VISIBLE
                addToBucket.visibility = View.VISIBLE

                reviewBlock.addReviews(listOf(), productRate.toFloat())

            }

        }
    }

    private suspend fun getFactory(id: String) {
        val dao = ServerController().factoryDAO

        val factories = dao.getFactory(id = id)
        if (factories.isNotEmpty()) {
            factory = factories.first()
        }

        setFactory()
    }

    private suspend fun setFactory() {
        withContext(Dispatchers.Main) {

            factory?.apply {
                titleFactory.text = factoryName

                factoryBlock.background = null
                titleFactory.visibility = View.VISIBLE
                factoryBtn.visibility = View.VISIBLE
            }

        }
    }

    private suspend fun getLiked(productType: Int) {
        val dao = ServerController().productDAO

        var products = dao.getProducts(type = listOf(productType))
        products = products.filter { it.productId != product?.productId }

        withContext(Dispatchers.Main) {
            if (products.isNotEmpty()) {
                cardFirst.addProduct(products[0])
                cardSecond.addProduct(products.getOrNull(1))
            }
        }
    }

}