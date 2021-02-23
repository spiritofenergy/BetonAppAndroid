package com.kodexgroup.betonapp.screens.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.screens.SecondFragment

class ProductListFragment : SecondFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)

//        addView(LayoutInflater.from(context).inflate(R.layout.frame_main, null))

        return root
    }

}