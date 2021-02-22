package com.kodexgroup.betonapp.utils.views

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kodexgroup.betonapp.R

class ButtonMenuView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    private var btn: LinearLayout
    private var mainIconBtn: ImageButton
    private var titleIconBtn: TextView

    var icon: Int = 0
        set(value) {
            field = value
            mainIconBtn.setImageResource(value)
        }

    var title: String = ""
        set(value) {
            field = value
            titleIconBtn.text = value
        }

    var background: Int = 0
        set(value) {
            field = value
            mainIconBtn.setBackgroundResource(value)
        }

    private var navController: NavController? = null

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.content_button_menu, this, true)

        if (!isInEditMode) {
            navController = Navigation.findNavController(context as Activity, R.id.nav_host_fragment)
        }

        btn = root.findViewById(R.id.btn_menu_main)
        mainIconBtn = root.findViewById(R.id.image_btn_menu)
        titleIconBtn = root.findViewById(R.id.title_btn_menu)

        btn.setOnClickListener {
            println("menu")

            navController?.navigate(R.id.to_product_list)
        }

        context.obtainStyledAttributes(attributeSet,
                R.styleable.ButtonMenuView, 0, 0)
                .apply {
                    try {
                        title = getString(R.styleable.ButtonMenuView_titleBtnMain) ?: "Акции"
                        icon = getResourceId(R.styleable.ButtonMenuView_iconBtnMain, R.drawable.ic_discount)
                        background = getResourceId(R.styleable.ButtonMenuView_backgroundBtn, R.drawable.ic_bg_menu_button_blue)
                    } finally { }
                }
                .recycle()
    }

    fun setOnClickListener(listener: () -> Unit) {
        btn.setOnClickListener {
            listener()
        }
    }

}