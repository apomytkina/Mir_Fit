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
import ru.hse.project.mirfit.util.Validator

class DialogEditPasswordFragment : DialogFragment() {
    private lateinit var editOldPassword: EditText
    private lateinit var editNewPassword: EditText
    private lateinit var btnCancel: Button
    private lateinit var btnComplete: Button
    private lateinit var user: BaseClient.User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = AuthActivity.client.currentUser!!
        val root = inflater.inflate(R.layout.dialog_fragment_edit_password, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        editOldPassword = root.findViewById(R.id.edit_password_old_field)
        editNewPassword = root.findViewById(R.id.edit_password_new_field)
        btnCancel = root.findViewById(R.id.edit_password_cancel_btn)
        btnComplete = root.findViewById(R.id.edit_password_save_btn)

        btnCancel.setOnClickListener {
            dismiss()
            DialogChoseEditFragment().show(parentFragmentManager, "EDIT_LOG_PAS")
        }

        btnComplete.setOnClickListener {
            val oldPassword = editOldPassword.text.toString()
            val newPassword = editNewPassword.text.toString()


            if (oldPassword != user.password) {
                editOldPassword.error = "Неверный пароль"
                return@setOnClickListener
            }

            val newPasswordValidate = Validator.validatePassword(newPassword)
            if(!newPasswordValidate.isValidate){
                editNewPassword.error=newPasswordValidate.validateError
                return@setOnClickListener
            }

            if (oldPassword == newPassword) {
                editNewPassword.error = "Новый пароль должен отличаться от старого"
                return@setOnClickListener
            }

            user.updatePassword(newPassword).addOnSuccessListener {
                dismiss()
                Toast.makeText(inflater.context, "successful update password", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                dismiss()
                Toast.makeText(inflater.context, it.message, Toast.LENGTH_SHORT).show()
            }
        }



        return root
    }
}