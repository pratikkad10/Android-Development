package com.example.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notesapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var db : DatabaseHelper

    private  var notesId = -1
    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        notesId = intent.getIntExtra("note_id", -1)
        if(notesId == -1){
            finish()
            return
        }

        val note = db.getNotesById(notesId)

        binding.updateTitle.setText(note.title)
        binding.updateNotes.setText(note.content)
        binding.updatecreatedAtText.setText(note.createdDate)

        binding.btnUpdatedone.setOnClickListener {
            val title = binding.updateTitle.text.toString()
            val content = binding.updateNotes.text.toString()
            val createdAt = binding.updatecreatedAtText.text.toString()

            val updatedNote = Notes(notesId, title, content, createdAt)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}