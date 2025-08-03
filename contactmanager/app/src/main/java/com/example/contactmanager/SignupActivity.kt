package com.example.contactmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactmanager.databinding.ActivityMainBinding
import com.example.contactmanager.databinding.ActivitySignupBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.existAcc.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))

        }

        val username = binding.etusername
        val email = binding.etemail
        val password = binding.etPass

        binding.signupBtn.setOnClickListener {
            Toast.makeText(this, "clicked signup ", Toast.LENGTH_SHORT).show()
            val testRef = FirebaseDatabase.getInstance().getReference("Test")
            testRef.setValue("Hello World")
                .addOnSuccessListener {
                    Toast.makeText(this, "Test write success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Test write failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }

//            if (username.text.isNullOrEmpty() || email.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            Toast.makeText(this, "clicked on signup btn", Toast.LENGTH_SHORT).show()
//            database = FirebaseDatabase.getInstance().getReference("Users")
//            val user = User(username.text.toString(), email.text.toString(), password.text.toString())
//            database.child(username.text.toString()).setValue(user).addOnSuccessListener {
//                Toast.makeText(this, "Signup successful ${username.text.toString()} !", Toast.LENGTH_LONG).show()
//                username.text?.clear()
//                email.text?.clear()
//                password.text?.clear()
//
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }.addOnFailureListener { e ->
//                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
//            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}