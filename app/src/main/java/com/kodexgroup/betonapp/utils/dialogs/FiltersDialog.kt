package com.kodexgroup.betonapp.utils.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.dpToPx

class FiltersDialog : DialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_filters, container, false)

        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        return root
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setGravity(Gravity.TOP or Gravity.END)
        dialog?.window?.setLayout(dpToPx(320f), ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }

    fun setOnDismissListener(listener: () -> Unit) {

    }

    override fun onDismiss(dialog: DialogInterface, ) {
        super.onDismiss(dialog)

    }

}