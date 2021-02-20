package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.database.server.entities.Product

class MiniCardProductView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val mainImage: ImageView
    private val title: TextView
    private val factoryName: TextView
    private val rate: TextView
    private val factoryAddress: TextView

    private val card: CardView

    private var product: Product? = null

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.content_mini_card_product, this, true)

        card = root.findViewById(R.id.product_card)

        mainImage = root.findViewById(R.id.preview_mini_card)
        title = root.findViewById(R.id.title_mini_card)
        factoryName = root.findViewById(R.id.factory_mini_card)
        rate = root.findViewById(R.id.rate_mini_card)
        factoryAddress = root.findViewById(R.id.address_mini_card)

        card.setOnClickListener {
            println("CLICK")

            // TODO("open product")
        }
    }

    fun addProduct(product: Product) {
        this.product = product

        title.text = product.productName
        factoryName.text = product.factory.factoryName
        rate.text = product.productName
        factoryAddress.text = product.factory.factoryAddress
    }

}