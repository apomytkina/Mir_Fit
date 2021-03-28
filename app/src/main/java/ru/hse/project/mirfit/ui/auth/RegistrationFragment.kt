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
            val firstNameValidate = Validator.validateUserName(firstName)
            if (!firstNameValidate.isValidate) {
                textFirstName.error = firstNameValidate.validateError
                return@setOnClickListener
            }


            val secondName = textSecondName.text.toString()
            val secondNameValidate = Validator.validateUserName(secondName)
            if (!secondNameValidate.isValidate) {
                textSecondName.error = secondNameValidate.validateError
                return@setOnClickListener
            }


            val patronymic = textPatronymic.text.toString()
            val patronymicValidate = Validator.validateUserName(patronymic)
            if (!patronymicValidate.isValidate) {
                textPatronymic.error = patronymicValidate.validateError
                return@setOnClickListener
            }

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
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}