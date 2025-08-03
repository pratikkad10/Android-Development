package com.example.signinsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    companion object{
        const val KEY1 = "com.example.signinsignup.name"
        const val KEY2 = "com.example.signinsignup.mail"
    }


    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val etName = findViewById<TextInputEditText>(R.id.ipName)
        val etPass = findViewById<TextInputEditText>(R.id.ipPass)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val name = etName.text.toString()
            val password = etPass.text.toString()

            if(name.isNotEmpty() && password.isNotEmpty()){
                readData(name, password);
            }else{
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun readData(name: String, password: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(name).get().addOnSuccessListener {
            if(it.exists()){
                val savedPass = it.child("password").value
                if(savedPass == password){
                    Toast.makeText(this, "Welcome Back $name", Toast.LENGTH_SHORT).show()
                    val mail = it.child("mail").value
                    val intent = Intent(this, WelcomeActivity::class.java)
                    intent.putExtra(KEY1, name)
                    intent.putExtra(KEY2, mail.toString())
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Password Incorrect! $savedPass", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "User does not exist!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}