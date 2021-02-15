package ru.hse.project.clientmir.ui.main.profile

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.hse.project.clientmir.R

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(newObject: CardObject) {
        val cardNumber = newObject.cardNumber
        itemView.findViewById<TextView>(R.id.lay_bank_card_last_digits).text =
            ("•••• " + cardNumber.subSequence(cardNumber.length - 5, cardNumber.length - 1))

        itemView.findViewById<TextView>(R.id.lay_bank_card_balance).text = (newObject.balance + "₽")
        itemView.findViewById<TextView>(R.id.lay_bank_card_name).text = newObject.cardName
    }
}

