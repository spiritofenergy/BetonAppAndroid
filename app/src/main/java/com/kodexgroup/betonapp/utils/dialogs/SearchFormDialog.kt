package com.kodexgroup.betonapp.utils.dialogs

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.screens.home.HomeFragment
import com.kodexgroup.betonapp.utils.views.DialogContentView
import com.kodexgroup.betonapp.utils.views.MiniCardProductView
import com.kodexgroup.betonapp.utils.views.SearchBlockView


class SearchFormDialog : DialogFragment() {

    private lateinit var searchLock: SearchBlockView
    private lateinit var history: DialogContentView
    private lateinit var specialForYou: LinearLayout
    private lateinit var popular: DialogContentView

    private var listener: (() -> Unit)? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_search, container, false)



        searchLock = root.findViewById(R.id.dialog_search_form)
        history = root.findViewById(R.id.history_search)
        popular = root.findViewById(R.id.add_search)
        specialForYou = root.findViewById(R.id.special_product)

        history.addMiniButtons(listOf("", "", ""))
        popular.addMiniButtons(listOf("", "", ""))

        history.setOnBtnClickListener {
            // TODO("DELETE HISTORY")
            println("Delete")
        }

        popular.setOnBtnClickListener {
            // TODO("RELOAD POPULAR")
            println("Reload")
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        val param = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1.0f
        )

        for (i in 1..2) {
            val card = MiniCardProductView(requireContext(), null)

            card.setOnClickCardListener {
                findNavController().popBackStack()
                val home = parentFragment?.childFragmentManager?.fragments?.get(0) as HomeFragment

                home.toProduct(it)

            }

            card.layoutParams = param
            specialForYou.addView(card)
        }
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

    override fun onDismiss(dialog: DialogInterface) {
        searchLock.clearFocusForm()


        findNavController().popBackStack()
        listener?.let { it() }
    }

    private fun setFocus() {
        searchLock.isFocus = true
    }

}