package ru.hse.project.mirfit.ui.profile.card

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
import ru.hse.project.mirfit.ui.auth.AuthActivity
import ru.hse.project.mirfit.util.Validator

class DialogEditCardFragment(private val cardAdapter: CardAdapter, private val position: Int) :
    DialogFragment() {

    private lateinit var editNameText: EditText
    private lateinit var btnCancel: Button
    private lateinit var btnComplete: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.dialog_fragment_edit_bank_card, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        editNameText = root.findViewById(R.id.edit_card_field)
        btnCancel = root.findViewById(R.id.edit_card_cancel_btn)
        btnComplete = root.findViewById(R.id.edit_card_save_btn)
        val oldNameCard = arguments?.getString("nameCard")
        editNameText.setText(oldNameCard)

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnComplete.setOnClickListener {
            val newName = editNameText.text.toString()
            val newNameValidate = Validator.validateLogin(newName)
            if(!newNameValidate.isValidate){
                editNameText.error=newNameValidate.validateError
                return@setOnClickListener
            }

            dismiss()

            AuthActivity.client.currentUser!!.editCard(newName, position)
                .addOnSuccessListener {
                    cardAdapter.editItem(newName, position)
                }.addOnFailureListener {
                    Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                }
        }
        return root
    }
}