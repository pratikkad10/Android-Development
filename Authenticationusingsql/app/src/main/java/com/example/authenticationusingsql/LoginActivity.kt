package com.example.authenticationusingsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.authenticationusingsql.databinding.ActivityLoginBinding
import com.example.authenticationusingsql.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.loginbtn.setOnClickListener {
            val loginEmail = binding.loginemail.text.toString()
            val loginPassword = binding.loginpassword.text.toString()

            if(loginEmail.isNotEmpty() && loginPassword.isNotEmpty()){
                loginUser(loginEmail, loginPassword)
            }else{
                Toast.makeText(this, "Enter valid input", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signuptextbtn.setOnClickListener {
            goToSignupActivity()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loginUser(loginEmail: String, loginPassword: String) {
        val user = databaseHelper.readUser(loginEmail, loginPassword)

        if(user){
            Toast.makeText(this, "Login successfull!", Toast.LENGTH_SHORT).show()
            goToMainActivity()
        }else{
            Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToSignupActivity() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}