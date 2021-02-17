package com.kodexgroup.betonapp.screens.app.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.kodexgroup.betonapp.R

class MapAppFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_app_map, container, false)

        val mainNavController =
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        return root
    }
}