package com.example.authenticationusingsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.authenticationusingsql.databinding.ActivitySignupBinding
import kotlinx.coroutines.selects.SelectInstance

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)

        binding.signupnbtn.setOnClickListener {
            val signupEmail = binding.signupemail.text.toString()
            val signupPassword = binding.signuppassword.text.toString()

            if(signupEmail.isNotEmpty() && signupPassword.isNotEmpty()){
                signupUser(signupEmail, signupPassword)
            }else{
                Toast.makeText(this, "Fill email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.logintextbtn.setOnClickListener {
            goToLoginActivity()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun signupUser(signupEmail: String, signupPassword: String) {
        val insertedRowId = databaseHelper.insertUser(signupEmail, signupPassword)

        if(insertedRowId != -1L){
            Toast.makeText(this, "Signup successfull!", Toast.LENGTH_SHORT).show()
            goToLoginActivity()
        }else{
            Toast.makeText(this, "Signup failed!", Toast.LENGTH_SHORT).show()

        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}

