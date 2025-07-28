package com.example.knowledgehub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val androidCard = findViewById<CardView>(R.id.androidCard)
        val webDevCard = findViewById<CardView>(R.id.webDevCard)
        val aimlCard = findViewById<CardView>(R.id.aimlCard)
        val javaCard = findViewById<CardView>(R.id.javaCard)
        val iosCard = findViewById<CardView>(R.id.iosCard)
        val uiDesignCard = findViewById<CardView>(R.id.uiDesignCard)
        val contactBtn = findViewById<Button>(R.id.contactBtn)

        androidCard.setOnClickListener {
            val intent = Intent(applicationContext, thirdActivity::class.java)
            intent.putExtra("url","https://www.android.com/")
            startActivity(intent)
        }

        webDevCard.setOnClickListener {
            val intent = Intent(applicationContext, thirdActivity::class.java)
            intent.putExtra("url","https://web.dev/learn/")
            startActivity(intent)
        }

        aimlCard.setOnClickListener {
            val intent = Intent(applicationContext, thirdActivity::class.java)
            intent.putExtra("url","https://www.geeksforgeeks.org/artificial-intelligence/aiml-introduction/")
            startActivity(intent)
        }

        javaCard.setOnClickListener {
            val intent = Intent(applicationContext, thirdActivity::class.java)
            intent.putExtra("url","https://www.java.com/en/")
            startActivity(intent)
        }

        iosCard.setOnClickListener {
            val intent = Intent(applicationContext, thirdActivity::class.java)
            intent.putExtra("url","https://developer.apple.com/ios/")
            startActivity(intent)
        }

        uiDesignCard.setOnClickListener {
            val intent = Intent(applicationContext, thirdActivity::class.java)
            intent.putExtra("url","https://www.figma.com/resource-library/what-is-ui-design/")
            startActivity(intent)
        }

        contactBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            val phonenumber= "9876543210"
            intent.data = Uri.parse("tel:$phonenumber")
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}