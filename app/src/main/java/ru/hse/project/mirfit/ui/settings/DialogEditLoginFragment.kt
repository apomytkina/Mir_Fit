package ru.hse.project.mirfit.ui.settings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient
import ru.hse.project.mirfit.ui.auth.AuthActivity

class DialogEditLoginFragment : DialogFragment() {


    private lateinit var editLogin: EditText
    private lateinit var btnCancel: Button
    private lateinit var btnComplete: Button
    private lateinit var user: BaseClient.User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        user = AuthActivity.client.currentUser!!
        val root = inflater.inflate(R.layout.dialog_fragment_edit_login, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        editLogin = root.findViewById(R.id.edit_login_field)
        btnCancel = root.findViewById(R.id.edit_login_cancel_btn)
        btnComplete = root.findViewById(R.id.edit_login_save_btn)
        editLogin.setText(user.login)



        btnCancel.setOnClickListener {
            dismiss()
            DialogChoseEditFragment().show(parentFragmentManager, "EDIT_LOG_PAS")
        }

        btnComplete.setOnClickListener {
            val newLogin = editLogin.text.toString()

            //validator newLogin

            user.updateLogin(newLogin).addOnSuccessListener {
                dismiss()
                Toast.makeText(context, "successful update login", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                dismiss()
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }

        }

        return root
    }
}