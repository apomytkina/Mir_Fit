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
import androidx.navigation.fragment.findNavController
import ru.hse.project.mirfit.MainActivity
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient
import ru.hse.project.mirfit.util.Validator


class RegistrationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_registration, container, false)

        val textFirstName = root.findViewById<EditText>(R.id.frag_reg_text_first_name)
        val textSecondName = root.findViewById<EditText>(R.id.frag_reg_text_second_name)
        val textPatronymic = root.findViewById<EditText>(R.id.frag_reg_text_patronymic)
        val textLogin = root.findViewById<EditText>(R.id.frag_reg_text_login)
        val textPassword = root.findViewById<EditText>(R.id.frag_reg_text_password)


        root.findViewById<Button>(R.id.frag_reg_btn).setOnClickListener {

            val firstName = textFirstName.text.toString()
            if (!Validator.validateString(firstName)) {
                textFirstName.error = ""
                return@setOnClickListener
            }

            val secondName = textSecondName.text.toString()
            if (!Validator.validateString(secondName)) {
                textSecondName.error = ""
                return@setOnClickListener
            }

            val patronymic = textPatronymic.text.toString()
            if (!Validator.validateString(patronymic)) {
                textPatronymic.error = ""
                return@setOnClickListener
            }

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


            AuthActivity.client.createUser(
                firstName,
                secondName,
                patronymic,
                login,
                password
            ).addOnSuccessListener {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                if (activity != null) {
                    activity?.finishAfterTransition()
                }
            }.addOnFailureListener {
                findNavController().navigate(R.id.navigation_auth)
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }


}