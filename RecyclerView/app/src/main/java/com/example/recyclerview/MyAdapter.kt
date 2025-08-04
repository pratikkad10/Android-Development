package com.example.recyclerview

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val arrayData :  ArrayList<Dataclass>, val context: Activity) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val currentItem = arrayData[position]
        holder.hTitle.text = currentItem.dataTitle
        holder.hImage.setImageResource(currentItem.dataImage)
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val hTitle = itemView.findViewById<TextView>(R.id.title)
        val hImage = itemView.findViewById<ImageView>(R.id.image)
    }
}