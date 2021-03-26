package com.j.thelist

import android.view.View
import android.widget.TextView
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.RecyclerView

class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var listPositionsTextView: TextView = itemView.findViewById<TextView>(R.id.item_number)
    var listPositonTitle: TextView = itemView.findViewById<TextView>(R.id.item_string)
}