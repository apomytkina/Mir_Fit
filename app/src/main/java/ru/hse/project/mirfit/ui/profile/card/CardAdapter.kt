package ru.hse.project.mirfit.ui.profile.card

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import ru.hse.project.mirfit.R
import ru.hse.project.mirfit.clientAuth.BaseClient
import ru.hse.project.mirfit.ui.auth.AuthActivity


class CardAdapter(private val fm: FragmentManager, private val data: ArrayList<CardObject>) :
    RecyclerSwipeAdapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.layout_bank_card, parent, false)
        return CardViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.swipeLayout.showMode = SwipeLayout.ShowMode.PullOut
        holder.swipeLayout.addDrag(
            SwipeLayout.DragEdge.Right,
            holder.swipeLayout.findViewById(R.id.bottom_wrapper)
        )

        holder.btnEdit.setOnClickListener {
            val editFrag = DialogEditCardFragment(this)
            editFrag.arguments = Bundle().apply {
                putString("NAME_CARD", item.cardName)
                putInt("POSITION", holder.adapterPosition)
            }
            //Создаем фрагмент по изменению имени карты
            editFrag.show(fm, "EDIT_CARD")
            holder.swipeLayout.close()
        }


        holder.btnDelete.setOnClickListener {
            AlertDialog.Builder(holder.swipeLayout.context)
                .setMessage("Вы точно хотите удалить карту с номером\n${item.cardNumber}")
                .setNegativeButton("Отмена") { dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss() }
                .setPositiveButton("Подтвердить") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    AuthActivity.client.currentUser!!.deleteCard(item).addOnSuccessListener {
                        mItemManger.removeShownLayouts(holder.swipeLayout)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, data.size)
                        mItemManger.closeAllItems()
                    }.addOnFailureListener {
                        holder.swipeLayout.close()
                        Toast.makeText(holder.swipeLayout.context, it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .create().show()
        }
        mItemManger.bindView(holder.itemView, position)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val swipeLayout: SwipeLayout = itemView.findViewById(R.id.swipe)
        val btnEdit: ConstraintLayout = itemView.findViewById(R.id.btn_bottom_wrapper_edit)
        val btnDelete: ConstraintLayout = itemView.findViewById(R.id.btn_bottom_wrapper_delete)


        fun bind(newObject: CardObject) {
            val cardNumber = newObject.cardNumber
            itemView.findViewById<TextView>(R.id.lay_bank_card_last_digits).text =
                ("•••• " + cardNumber.subSequence(cardNumber.length - 5, cardNumber.length - 1))

            itemView.findViewById<TextView>(R.id.lay_bank_card_balance).text =
                (newObject.balance.toString() + "₽")
            itemView.findViewById<TextView>(R.id.lay_bank_card_name).text = newObject.cardName
        }
    }

}

