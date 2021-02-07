package ru.hse.project.clientmir.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.hse.project.clientmir.MainActivity
import ru.hse.project.clientmir.R
import ru.hse.project.clientmir.appClient.BaseAuth


class RegistrationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val baseAuth = BaseAuth(inflater.context)
        val root = inflater.inflate(R.layout.fragment_registration, container, false)

        root.findViewById<Button>(R.id.frag_reg_btn).setOnClickListener {

            val updateUI = { successful: Boolean, message: String ->
                if (successful) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    activity?.finishAfterTransition()
                } else {
                    findNavController().navigate(R.id.navigation_auth)
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }


            //Проверки на валидность данных




            // baseAuth.createUser(updateUI)
            updateUI.invoke(true,null.toString())
        }

        return root
    }


}