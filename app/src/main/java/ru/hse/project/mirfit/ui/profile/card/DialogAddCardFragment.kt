package ru.hse.project.mirfit.ui.profile.card

import android.content.Context
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

class DialogAddCardFragment(private val cardAdapter: CardAdapter) : DialogFragment() {
    private lateinit var cardNameText: EditText
    private lateinit var cardNumberText: EditText
    private lateinit var btnAdd: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_fragment_add_bank_card, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        cardNumberText = root.findViewById(R.id.edit_text_card_number_frag_add_card)
        cardNameText = root.findViewById(R.id.edit_text_name_card_frag_add_card)
        btnAdd = root.findViewById(R.id.btn_frag_add_card)

        btnAdd.setOnClickListener {
            val cardNumber = cardNumberText.text.toString()
            val cardNumberValidate = Validator.validateNumberOfCard(cardNumber)
            if (!cardNumberValidate.isValidate) {
                cardNumberText.error = cardNumberValidate.validateError
                return@setOnClickListener
            }

            val cardName = cardNameText.text.toString()
            val cardNameValidate = Validator.validateNameCard(cardName)
            if (!cardNameValidate.isValidate) {
                cardNameText.error = cardNameValidate.validateError
                return@setOnClickListener
            }

            val card = CardObject(
                0.0,
                cardNumber,
                cardName,
                null,
                AuthActivity.client.currentUser!!.id
            )

            dismiss()


            AuthActivity.client.currentUser!!.addCard(card).addOnSuccessListener {
                cardAdapter.notifyItemInserted(cardAdapter.itemCount - 1)
            }.addOnFailureListener {
                Toast.makeText(inflater.context, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

}