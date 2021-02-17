package com.kodexgroup.betonapp.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.dialogs.SearchFormDialog

class SearchFormView(context: Context, attributeSet: AttributeSet) : LinearLayout(
    context,
    attributeSet
) {

    private val searchLock: SearchBlockView

    private lateinit var fm: FragmentManager

    init {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.content_search_form, this, true)

        searchLock = root.findViewById(R.id.search_lock)

        if (!isInEditMode) {
            val fragmentActivity: FragmentActivity = context as FragmentActivity
            fm = fragmentActivity.supportFragmentManager
        }

        searchLock.lock {

            searchLock.setFocus {
                val dialog = SearchFormDialog()
                dialog.show(fm, "")

                dialog.setOnDismissListener {
                    val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.anim_fade_in)
                    searchLock.startAnimation(animFadeIn)

                    searchLock.clearFocusForm()
                }
            }

        }

    }

}