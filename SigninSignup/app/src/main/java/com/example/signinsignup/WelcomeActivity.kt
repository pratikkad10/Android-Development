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

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvMail= findViewById<TextView>(R.id.tvEmail)
        val logoutBtn = findViewById<Button>(R.id.btnLogout)

        tvName.text = intent.getStringExtra(LoginActivity.KEY1)
        tvMail.text = intent.getStringExtra(LoginActivity.KEY2)

        logoutBtn.setOnClickListener {
            Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}