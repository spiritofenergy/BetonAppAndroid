package com.kodexgroup.betonapp.screens.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.screens.HomeFragment

class MainAppFragment : HomeFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)

        addView(LayoutInflater.from(context).inflate(R.layout.content_home, null))

        return root
    }

}