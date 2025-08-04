package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var MyRecyclerView : RecyclerView
    private lateinit var arrayData : ArrayList<Dataclass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //get the recycler view
        MyRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val imageArray = arrayOf(
            R.drawable.toggle,
            R.drawable.image,
            R.drawable.star,
            R.drawable.checkbox,
            R.drawable.clock)

        val titleArray= arrayOf(
            "Toggle",
            "Image",
            "Star",
            "checkbox",
            "clock"
        )

        MyRecyclerView.layoutManager = LinearLayoutManager(this)
        arrayData = arrayListOf<Dataclass>()
        for(idx in imageArray.indices){
            val item = Dataclass(imageArray[idx], titleArray[idx])
            arrayData.add(item)
        }

        MyRecyclerView.adapter = MyAdapter(arrayData, this)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}