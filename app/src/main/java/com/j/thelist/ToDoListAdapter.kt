package com.j.thelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter : RecyclerView.Adapter<ToDoListViewHolder>() {


    private var toDoLists = mutableListOf<String>("Android Development", "Housework", "Errands", "Other")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.to_do_list_view_holder, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.listPositionsTextView.text = (position + 1 ).toString()
        holder.listPositonTitle.text = toDoLists[position]
    }

    override fun getItemCount(): Int {
        return toDoLists.size
    }

    fun addNewItem(list : String = ""){
        if(list.isEmpty()){
            toDoLists.add("To Do List " + (toDoLists.size + 1))
        }else{
            toDoLists.add(list)
        }
        notifyDataSetChanged()
    }

}