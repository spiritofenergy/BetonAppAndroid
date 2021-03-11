package com.kodexgroup.betonapp.screens.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.screens.SecondFragment
import com.kodexgroup.betonapp.utils.controllers.ProductFragmentController

class ProductFragment : SecondFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = super.onCreateView(inflater, container, savedInstanceState)

        val id = arguments?.getString("productId") ?: ""

        addView(inflater.inflate(R.layout.content_product, null))
        ProductFragmentController(requireContext(), root, id)


        return root
    }

}