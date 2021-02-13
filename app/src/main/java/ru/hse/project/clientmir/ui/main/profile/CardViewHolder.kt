package ru.hse.project.clientmir.ui.main.profile

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.hse.project.clientmir.R
import java.lang.IllegalArgumentException

class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(newObject: CardObject) {
        itemView.findViewById<TextView>(R.id.post_title_name).text = newObject.title
        itemView.findViewById<TextView>(R.id.post_desc_text).text = newObject.content
        itemView.findViewById<TextView>(R.id.post_date_text).text = newObject.dateOfPublish


    }
}

