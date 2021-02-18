package com.kodexgroup.betonapp.utils.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.views.DialogContentView
import com.kodexgroup.betonapp.utils.views.SearchBlockView


class SearchFormDialog : DialogFragment() {

    private lateinit var searchLock: SearchBlockView
    private lateinit var history: DialogContentView
    private lateinit var add: DialogContentView

    private var listener: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_search, container, false)

        val toolbar = root.findViewById<Toolbar>(R.id.toolbar_dialog)

        searchLock = root.findViewById(R.id.dialog_search_form)
        history = root.findViewById(R.id.history_search)
        add = root.findViewById(R.id.add_search)

        history.addMiniButtons(listOf("", "", ""))
        add.addMiniButtons(listOf("", "", ""))

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

        listener?.let { it() }
    }

    private fun setFocus() {
        searchLock.isFocus = true
    }

}