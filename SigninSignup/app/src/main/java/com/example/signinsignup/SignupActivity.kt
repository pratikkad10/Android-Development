package com.example.signinsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        val signupBtn = findViewById<Button>(R.id.signupBtn)
        val existBtn = findViewById<TextView>(R.id.existAcc)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val etPass = findViewById<TextInputEditText>(R.id.etPass)

        signupBtn.setOnClickListener {

            val name = etName.text.toString()
            val email = etMail.text.toString()
            val password = etPass.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(name, email, password)
            database.child(name).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etPass.text?.clear()
                etMail.text?.clear()
                Toast.makeText(this, "Signup successful $name !", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()
            }

        }

        existBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}