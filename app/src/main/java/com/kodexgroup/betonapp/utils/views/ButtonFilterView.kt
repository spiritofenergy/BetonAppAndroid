package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.findParent

class ButtonFilterView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val button: TextView
    private val activeView: View

    var isInFilterView = false

    private var listener: (() -> Unit)? = null

    var isActive: Boolean = false
        set(value) {
            field = value

            if (value) {
                button.setBackgroundResource(R.drawable.ic_filter_border_active)
                button.setTextColor(ContextCompat.getColor(context, R.color.yellow_700))
                activeView.visibility = View.VISIBLE
            } else {
                button.setBackgroundResource(R.drawable.ic_filter_border_normal)
                button.setTextColor(ContextCompat.getColor(context, R.color.hint))
                activeView.visibility = View.INVISIBLE
            }
        }

    var text: String = ""
        set(value) {
            field = value

            button.text = text
        }

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.view_filter_button, this, true)

        button = root.findViewById(R.id.btn_filter)
        activeView = root.findViewById(R.id.active_btn)

        context.obtainStyledAttributes(attrs,
                R.styleable.ButtonFilterView, 0, 0)
                .apply {
                    try {
                        isActive = getBoolean(R.styleable.ButtonFilterView_active, false)
                        text = getString(R.styleable.ButtonFilterView_text) ?: "Button"
                    } finally { }
                }
                .recycle()

        onClick()
    }

    fun setOnClickBtnListener(listener: () -> Unit) {
        this.listener = listener
        onClick()
    }

    private fun onClick() {
        button.setOnClickListener {
            isActive = !isActive

            listener?.invoke()

            if (isInFilterView) {
                findParent<FiltersView>(parent)?.deleteBtn(this)
            }
        }
    }
}