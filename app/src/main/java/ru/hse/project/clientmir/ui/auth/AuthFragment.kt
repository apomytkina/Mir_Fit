package ru.hse.project.clientmir.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.hse.project.clientmir.R

class AuthFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_selection_auth, container, false)

        root.findViewById<Button>(R.id.btn_reg).setOnClickListener{
            findNavController().navigate(R.id.navigation_registration)
        }
        root.findViewById<Button>(R.id.btn_signIn).setOnClickListener{
            findNavController().navigate(R.id.navigation_signin)
        }
        return root
    }
}