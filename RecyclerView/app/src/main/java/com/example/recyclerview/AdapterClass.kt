package com.example.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: ArrayList<Dataclass>): RecyclerView.Adapter<AdapterClass.ViewHolderClas>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderClas {

    }

    override fun onBindViewHolder(
        holder: ViewHolderClas,
        position: Int
    ) {

    }

    override fun getItemCount(): Int {
        
    }

    class ViewHolderClas(view: View):RecyclerView.ViewHolder(itemView) {

    }
}