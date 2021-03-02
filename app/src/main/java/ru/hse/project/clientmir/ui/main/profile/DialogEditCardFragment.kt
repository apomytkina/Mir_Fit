package ru.hse.project.clientmir.ui.main.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import ru.hse.project.clientmir.R
import ru.hse.project.clientmir.util.Validator

class DialogEditCardFragment(private val cardAdapter: CardAdapter, private val position: Int) :
    DialogFragment() {

    lateinit var editName: EditText
    lateinit var btnCancel: Button
    lateinit var btnComplete: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.dialog_fragment_edit_bank_card, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        editName = root.findViewById(R.id.dfrag_editcard_edit_name)
        btnCancel = root.findViewById(R.id.dfrag_edit_card_cancel)
        btnComplete = root.findViewById(R.id.dfrag_edit_card_complete)
        val oldNameCard = arguments?.getString("nameCard")
        editName.setText(oldNameCard)


        btnCancel.setOnClickListener {
            dismiss()
        }

        btnComplete.setOnClickListener {
            val newName = editName.text.toString()
            if (!Validator.validateString(newName)) {
                editName.error = "invalid Name"
                return@setOnClickListener
            }

            cardAdapter.editItem(newName, position)
            dismiss()
        }
        return root
    }
}