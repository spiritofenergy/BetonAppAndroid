package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.database.server.entities.Factory
import com.kodexgroup.betonapp.utils.getImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class FactoryCardView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val card: CardView
    private val titleTxt: TextView
    private val previewImage: ImageView
    private val saleTxt: TextView
    private val timeTo: TextView
    private val favorite: ImageButton
    private val rateTxt: TextView
    private val typeView: TextView
    private val priceTxt: TextView
    private val subText: TextView

    var sale: Int = 0
        set(value) {
            field = value
            if (value == 0) {
                saleTxt.visibility = View.INVISIBLE
            } else {
                saleTxt.visibility = View.VISIBLE
                saleTxt.text = "$value% скидка"
            }
        }

    var time: Int = 0
        set(value) {
            field = value

            timeTo.text = "$value мин"
        }

    private var factory: Factory? = null


    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.content_factory_card, this, true)

        card = root.findViewById(R.id.factory_card)
        titleTxt = root.findViewById(R.id.title_factory_card)
        previewImage = root.findViewById(R.id.preview_factory_card)
        saleTxt = root.findViewById(R.id.sale_factory_card)
        timeTo = root.findViewById(R.id.time__factory_card)
        favorite = root.findViewById(R.id.add_favorite_factory_card)
        rateTxt = root.findViewById(R.id.rate_factory_card)
        typeView = root.findViewById(R.id.type_factory_card)
        priceTxt = root.findViewById(R.id.price_factory_card)
        subText = root.findViewById(R.id.sub_text_factory_card)

        card.setOnClickListener {
            println("Factory")

            // TODO("ADD FACTORY")
        }

        favorite.setOnClickListener {
            println("Favorite")

            // TODO("ADD FAVORITE")
        }

        CoroutineScope(Dispatchers.Main).launch {
            getImage(R.drawable.factory, previewImage)
        }
    }

    fun addFactory(factory: Factory) {
        this.factory = factory


    }

}