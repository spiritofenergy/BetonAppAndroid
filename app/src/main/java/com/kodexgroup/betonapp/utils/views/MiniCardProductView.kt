package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.database.server.ServerController
import com.kodexgroup.betonapp.database.server.entities.Product
import com.kodexgroup.betonapp.utils.getImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MiniCardProductView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val mainImage: ImageView
    private val title: TextView
    private val factoryName: TextView
    private val sale: TextView
    private val percent: TextView
    private val countBuy: TextView
    private val price: TextView
    private val priceStrike: TextView

    private var isSale: Boolean = false
        set(value) {
            field = value

            if (value) {
                sale.visibility = VISIBLE
                priceStrike.visibility = VISIBLE
                percent.visibility = VISIBLE

            } else {
                sale.visibility = GONE
                priceStrike.visibility = GONE
                percent.visibility = GONE
            }
        }

    private val card: CardView

    private var product: Product? = null

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.view_mini_card_product, this, true)

        card = root.findViewById(R.id.product_card)

        mainImage = root.findViewById(R.id.preview_mini_card)
        title = root.findViewById(R.id.title_mini_card)
        factoryName = root.findViewById(R.id.factory_mini_card)
        sale = root.findViewById(R.id.sale_mini_card)
        percent = root.findViewById(R.id.textView10)
        countBuy = root.findViewById(R.id.count_buy_mini_card)
        price = root.findViewById(R.id.price_mini_card)
        priceStrike = root.findViewById(R.id.price_throwline_mini_card)

        card.setOnClickListener {
            println("CLICK")

            // TODO("open product")
        }

        CoroutineScope(Dispatchers.Main).launch {
            getImage(R.drawable.concrete, mainImage)
        }
    }

    fun addProduct(product: Product) {
        this.product = product

        title.text = product.productName
        getFactory(product.factory)
        countBuy.text = context.getString(R.string.count_buy_mini_card, product.productCountOrders)
        var priceTotal = product.productPrice

        if (product.productSale != 0) {
            isSale = true

            sale.text = context.getString(R.string.percent_sale_mini_card, product.productSale)
            priceStrike.text = context.getString(R.string.sale_price_strike, priceTotal)
            priceStrike.paintFlags = priceStrike.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            priceTotal -= (priceTotal * product.productSale / 100)
            price.text = context.getString(R.string.price_mini_card, priceTotal)
        } else {
            isSale = false
            price.text = context.getString(R.string.price_mini_card, priceTotal)
        }
    }

    private fun getFactory(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val dao = ServerController().factoryDAO

            val factory = dao.getFactory(id = id)
            factoryName.text = if (factory.isNotEmpty()) {
                factory.first().factoryName
            } else {
                ""
            }
        }
    }

}