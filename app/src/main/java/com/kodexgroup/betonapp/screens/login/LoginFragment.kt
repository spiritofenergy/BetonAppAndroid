package com.kodexgroup.betonapp.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.kodexgroup.betonapp.R
import com.kodexgroup.betonapp.utils.views.EditTextHintView
import com.kodexgroup.betonapp.utils.views.LoginFormView

class LoginFragment : Fragment() {

    private lateinit var loginForm: LoginFormView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        loginForm = root.findViewById(R.id.login_form)

        return root
    }

    override fun onResume() {
        super.onResume()

        loginForm.clear()
    }

}