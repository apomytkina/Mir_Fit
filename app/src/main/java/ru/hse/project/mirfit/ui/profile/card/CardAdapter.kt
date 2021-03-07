package ru.hse.project.mirfit.ui.profile.card

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import ru.hse.project.mirfit.R


class CardAdapter(private val fm: FragmentManager, private val data: ArrayList<CardObject>) :
    RecyclerSwipeAdapter<CardAdapter.CardViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
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
            val editFrag = DialogEditCardFragment(this, holder.adapterPosition)
            editFrag.arguments = Bundle().apply { putString("nameCard", item.cardName) }
            //Создаем фрагмент по изменению имени карты
            editFrag.show(fm, "EDIT_CARD")
            holder.swipeLayout.close()
        }


        holder.btnDelete.setOnClickListener {
            mItemManger.removeShownLayouts(holder.swipeLayout)
            data.remove(item)
            // BaseClient(context).currentUser.updateCardObject()
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
            mItemManger.closeAllItems()
        }

        mItemManger.bindView(holder.itemView, position)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }


    fun addItem(cardObject: CardObject) {
        data.add(cardObject)
        //  BaseClient(context).currentUser.updateCardObject()
        this.notifyItemInserted(data.size - 1)
    }

    fun editItem(newName: String, position: Int) {
        data[position].cardName = newName
        //BaseClientAuth().currentUser.updateCardObject()
        notifyItemChanged(position)
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
                (newObject.balance + "₽")
            itemView.findViewById<TextView>(R.id.lay_bank_card_name).text = newObject.cardName
        }
    }

}

