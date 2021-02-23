package com.kodexgroup.betonapp.utils.views

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.findParentNavController
import com.kodexgroup.betonapp.utils.getImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ButtonTypeView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    private val btn: CardView
    private val previewType: ImageView
    private val titleTypeTxt: TextView

    private var navController: NavController? = null

    var title: String = ""
        set(value) {
            field = value

            titleTypeTxt.text = value
        }

    var preview: Int = R.drawable.concrete
        set(value) {
            field = value

            CoroutineScope(Dispatchers.Main).launch {
                getImage(R.drawable.concrete, previewType)
            }
        }

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.view_menu_types, this, true)

        if (!isInEditMode) {
            navController = findParentNavController()
        }

        btn = root.findViewById(R.id.type_menu_btn)
        previewType = root.findViewById(R.id.preview_type_card)
        titleTypeTxt = root.findViewById(R.id.title_type_card)

        context.obtainStyledAttributes(attributeSet,
                R.styleable.ButtonTypeView, 0, 0)
                .apply {
                    try {
                        title = getString(R.styleable.ButtonTypeView_typeName) ?: "Бетоны"
                        preview = getResourceId(R.styleable.ButtonTypeView_typePreview, R.drawable.concrete)
                    } finally { }
                }
                .recycle()

        btn.setOnClickListener {
            println("search")

            navController?.navigate(R.id.to_product_list)
        }
    }

}