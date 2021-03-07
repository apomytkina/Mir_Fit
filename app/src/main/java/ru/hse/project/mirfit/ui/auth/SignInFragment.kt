package ru.hse.project.mirfit.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.hse.project.mirfit.MainActivity
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient
import ru.hse.project.mirfit.util.Validator

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val baseAuth = BaseClient(inflater.context)
        val root = inflater.inflate(R.layout.fragment_signin, container, false)

        val textLogin = root.findViewById<EditText>(R.id.frag_sign_login)
        val textPassword = root.findViewById<EditText>(R.id.frag_sign_password)

        root.findViewById<Button>(R.id.frag_sign_btn).setOnClickListener {

            val login = textLogin.text.toString()
            if (!Validator.validateString(login)) {
                textLogin.error = ""
                return@setOnClickListener
            }

            val password = textPassword.text.toString()
            if (!Validator.validateString(password)) {
                textPassword.error = ""
                return@setOnClickListener
            }


            val updateUI = { successful: Boolean ->
                if (successful) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    if (activity != null) {
                        activity?.finishAfterTransition()
                    }
                } else {
                    findNavController().navigate(R.id.navigation_auth)
                }
            }

            baseAuth.signInUser(updateUI, login, password)
        }

        return root
    }
}