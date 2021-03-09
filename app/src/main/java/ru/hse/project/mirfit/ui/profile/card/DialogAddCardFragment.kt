package ru.hse.project.mirfit.ui.profile.card

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient
import ru.hse.project.mirfit.ui.auth.AuthActivity
import ru.hse.project.mirfit.util.Validator

class DialogAddCardFragment(private val postAdapter: CardAdapter) : DialogFragment() {
    private lateinit var cardName: EditText
    private lateinit var cardNumber: EditText
    private lateinit var btnAdd: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.dialog_fragment_add_bank_card, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        cardNumber = root.findViewById(R.id.edit_text_card_number_frag_add_card)
        cardName = root.findViewById(R.id.edit_text_name_card_frag_add_card)
        btnAdd = root.findViewById(R.id.btn_frag_add_card)

        btnAdd.setOnClickListener {
            val cardNumberText = cardNumber.text.toString()
            val cardNameText = cardName.text.toString()

            if (!Validator.validateNumber(cardNumberText)) {
                cardNumber.error = "Invalid Card Number"
                return@setOnClickListener
            }

            if (!Validator.validateString(cardNameText)) {
                cardName.error = "Invalid Card Name"
                return@setOnClickListener
            }


            val card = CardObject(
                0,
                cardNumberText,
                cardNameText,
                0,
                AuthActivity.client.currentUser!!.id
            )
            dismiss()

            AuthActivity.client.currentUser!!.addCard(card).addOnSuccessListener {
                postAdapter.addItem(card)
            }
        }

        return root
    }

}