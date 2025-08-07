package com.example.notesapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var notes : List<Notes>, context: Context):
    RecyclerView.Adapter<NoteAdapter.NoteviewHolder>() {

        private var db : DatabaseHelper = DatabaseHelper(context)

        override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteviewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NoteviewHolder,
        position: Int
    ) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.createdDateView.text = note.createdDate

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        
        holder.deleteButton.setOnClickListener { 
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun refreshData(newNotes: List<Notes>){
        notes = newNotes
        notifyDataSetChanged()
    }

    class NoteviewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById<TextView>(R.id.contentTextView)
        val createdDateView: TextView = itemView.findViewById<TextView>(R.id.dateTextView)

        val updateButton : ImageView = itemView.findViewById<ImageView>(R.id.editIcon)

        val deleteButton : ImageView = itemView.findViewById<ImageView>(R.id.deleteIcon)
    }
}