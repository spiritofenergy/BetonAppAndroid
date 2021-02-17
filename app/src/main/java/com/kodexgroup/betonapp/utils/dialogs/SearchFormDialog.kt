package com.kodexgroup.betonapp.utils.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.views.SearchBlockView


class SearchFormDialog : DialogFragment() {

    private lateinit var searchLock: SearchBlockView
    private lateinit var listener: () -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_search, container, false)

        searchLock = root.findViewById(R.id.dialog_search_form)

        return root
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setGravity(Gravity.TOP or Gravity.START)
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        setFocus()
    }

    fun setOnDismissListener(listener: () -> Unit) {
        this.listener = listener
    }

    override fun onDismiss(dialog: DialogInterface, ) {
        super.onDismiss(dialog)
        searchLock.clearFocusForm()

        listener()
    }

    private fun setFocus() {
        searchLock.isFocus = true
    }

}