package com.example.customadapter

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    lateinit var userArrayList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val name = arrayOf("rohit", "virat", "rishabh", "rahul", "ishan")
        val contact = arrayOf("8965547859", "3265894857", "421986532","9887564889", "7859461382")
        val img = arrayOf(R.drawable.profile, R.drawable.profile, R.drawable.profile, R.drawable.profile, R.drawable.profile)


        userArrayList = ArrayList()

        for(eachIndex in name.indices){
            val user = User(name[eachIndex], contact[eachIndex], img[eachIndex])
            userArrayList.add(user)
        }

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = MyAdapter(this, userArrayList)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}