package com.example.themetoggleapp

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val darkBtn = findViewById<Button>(R.id.darkbtn)
        val readBtn = findViewById<Button>(R.id.readbtn)
        val linearlayout = findViewById<LinearLayout>(R.id.linearlayout)


        darkBtn.setOnClickListener {
            linearlayout.setBackgroundResource(R.color.black)
        }

        readBtn.setOnClickListener {
            linearlayout.setBackgroundResource(R.color.yellow)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearlayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}