package ru.hse.project.mirfit.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.hse.project.mirfit.MainActivity
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.util.Validator

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_signin, container, false)

        val textLogin = root.findViewById<EditText>(R.id.frag_sign_login)
        val textPassword = root.findViewById<EditText>(R.id.frag_sign_password)

        root.findViewById<Button>(R.id.frag_sign_btn).setOnClickListener {

            val login = textLogin.text.toString()
            val loginValidate = Validator.validateLogin(login)
            if (!loginValidate.isValidate) {
                textLogin.error = loginValidate.validateError
                return@setOnClickListener
            }

            val password = textPassword.text.toString()
            val passwordValidate = Validator.validatePassword(password)
            if (!passwordValidate.isValidate) {
                textPassword.error = passwordValidate.validateError
                return@setOnClickListener
            }


            AuthActivity.client.signInUser(login, password).addOnSuccessListener {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                if (activity != null) {
                    activity?.finishAfterTransition()
                }
            }.addOnFailureListener {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}