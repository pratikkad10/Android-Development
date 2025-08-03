package com.example.fragmentconcept

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.fragmentconcept.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var fragmentManager: androidx.fragment.app.FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFragment1.setOnClickListener {
            goToFragment(Fragment1())
        }

        binding.btnFragment2.setOnClickListener {
            goToFragment(Fragment2())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun goToFragment(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()

    }
}


