package com.example.examapp

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progLangbutn.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("your favourite programming language?")
            val choices = arrayOf("c++", "java", "js", "ts")
            builder1.setMultiChoiceItems(choices, null, DialogInterface.OnMultiChoiceClickListener{dialog, which, isChecked ->
                Toast.makeText(this, "clicked on ${choices[which]}", Toast.LENGTH_SHORT).show()
            })

            builder1.setPositiveButton("submit") { dialog, _ ->
                Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
            }

            builder1.setNegativeButton("skip") { dialog, _ ->
                dialog.dismiss()
            }

            builder1.show()
        }

        binding.gameBtn.setOnClickListener {
            val builder2 = AlertDialog.Builder(this)
            builder2.setTitle("your favourite online game?")
            val games = arrayOf("BGMI", "Free fire", "RC 24", "WCC3")
            builder2.setSingleChoiceItems(games, 0) { dialog, which ->
                Toast.makeText(this, "clicked on ${games[which]}", Toast.LENGTH_SHORT).show()
            }

            builder2.setPositiveButton("submit") { dialog, _ ->
                Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
            }

            builder2.setNegativeButton("skip") { dialog, _ ->
                dialog.dismiss()
            }

            builder2.show()
        }

        binding.permissionBtn.setOnClickListener {
            val builder3= AlertDialog.Builder(this)
            builder3.setTitle("Are you sure to exit?")
            builder3.setPositiveButton("exit", DialogInterface.OnClickListener{ Dialog, i ->
                finish()
            })
            builder3.setNegativeButton("no",DialogInterface.OnClickListener{ Dialog, i ->
                Dialog.dismiss()
            })
            builder3.show()
        }
        
        binding.exitBtn.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}