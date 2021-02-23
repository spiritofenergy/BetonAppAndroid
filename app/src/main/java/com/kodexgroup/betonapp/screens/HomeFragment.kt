package com.kodexgroup.betonapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.kodexgroup.betonapp.R

open class HomeFragment : Fragment() {

    private lateinit var frame: FrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.pattern_main, container, false)

        frame = root.findViewById(R.id.frame_main)

        return root
    }

    fun addView(view: View) {
        frame.addView(view)
    }

}