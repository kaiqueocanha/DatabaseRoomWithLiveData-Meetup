package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var items: List<Note> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val content: TextView = itemView.findViewById(R.id.content)
        val author: TextView = itemView.findViewById(R.id.author)
    }

    fun setData(newItems: List<Note>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun getData(position: Int): Note {
        return items[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = "ID: ${items[position].uuid}"
        holder.content.text = items[position].content
        holder.author.text = "Author: ${items[position].author}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }
}