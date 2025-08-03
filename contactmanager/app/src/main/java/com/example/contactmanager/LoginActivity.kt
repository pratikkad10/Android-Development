package com.example.contactmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactmanager.databinding.ActivityLoginBinding
import com.example.contactmanager.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.ipusername
        val password = binding.ippassword

        binding.loginBtn.setOnClickListener {
            if(username.text.toString().isEmpty() && password.text.toString().isEmpty() ){
                readData(username.text.toString(), password.text.toString())
            }else{
                Toast.makeText(this, "Inputs should not be empty", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun LoginActivity.readData(username: String, password:String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(username).get().addOnSuccessListener {
            if(it.exists()){
                val savedPass = it.child("password").value
                if(savedPass == password){
                    Toast.makeText(this, "Welcome Back $username", Toast.LENGTH_SHORT).show()
                    val email = it.child("email").value
//                    val intent = Intent(this, WelcomeActivity::class.java)
//                    intent.putExtra(KEY1, name)
//                    intent.putExtra(KEY2, mail.toString())
//                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Username $username does not exist!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }
    }
}


