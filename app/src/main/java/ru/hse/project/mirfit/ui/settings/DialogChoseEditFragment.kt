package ru.hse.project.mirfit.ui.settings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.ui.auth.AuthActivity
import ru.hse.project.mirfit.ui.profile.card.CardObject
import ru.hse.project.mirfit.util.Validator

class DialogChoseEditFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.dialog_fragment_edit_login_password, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        root.findViewById<ConstraintLayout>(R.id.btn_change_login).setOnClickListener {
            dismiss()
            DialogEditLoginFragment().show(parentFragmentManager, "EDIT_LOG")
        }

        root.findViewById<ConstraintLayout>(R.id.btn_change_password).setOnClickListener {
            dismiss()
            DialogEditPasswordFragment().show(parentFragmentManager, "EDIT_PAS")
        }

        root.findViewById<ConstraintLayout>(R.id.btn_change_login_password_cancel)
            .setOnClickListener {
                dismiss()
            }


        return root
    }
}