package ru.hse.project.clientmir.ui.auth

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
import ru.hse.project.clientmir.MainActivity
import ru.hse.project.clientmir.R
import ru.hse.project.clientmir.clientAuth.BaseClientAuth
import ru.hse.project.clientmir.util.Validator


class RegistrationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val baseAuth = BaseClientAuth(inflater.context)
        val root = inflater.inflate(R.layout.fragment_registration, container, false)

        val textFirstName = root.findViewById<EditText>(R.id.frag_reg_text_first_name)
        val textSecondName = root.findViewById<EditText>(R.id.frag_reg_text_second_name)
        val textPatronymic = root.findViewById<EditText>(R.id.frag_reg_text_patronymic)
        val textLogin = root.findViewById<EditText>(R.id.frag_reg_text_login)
        val textPassword = root.findViewById<EditText>(R.id.frag_reg_text_password)
        val textCardNumber = root.findViewById<EditText>(R.id.frag_reg_text_card_number)


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

            val cardNumber = textCardNumber.text.toString()
            if (!Validator.validateString(cardNumber)) {
                textCardNumber.error = ""
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


            val updateUI = { successful: Boolean, message: String ->
                if (successful) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    if (activity != null) {
                        activity?.finishAfterTransition()
                    }
                } else {
                    findNavController().navigate(R.id.navigation_auth)
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }

//            baseAuth.createUser(
//                updateUI,
//                firstName,
//                secondName,
//                patronymic,
//                cardNumber.toLong(),
//                login,
//                password
//            )


             updateUI.invoke(true, null.toString())
        }

        return root
    }


}