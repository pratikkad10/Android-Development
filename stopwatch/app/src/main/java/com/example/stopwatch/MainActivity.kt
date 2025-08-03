package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var isRunning = false
    private var timerSeconds = 0

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable{
        override fun run() {
            timerSeconds++
            val hours = timerSeconds / 3600
            val minutes = (timerSeconds % 3600)/60
            val seconds = timerSeconds % 60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.timerText.text = time

            handler.postDelayed(this, 1000)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startbtn.setOnClickListener {
            startTimer()
        }

        binding.resetbtn.setOnClickListener {
            resetTimer()
        }

        binding.stopbtn.setOnClickListener {
            stopTimer()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startTimer(){
        if(!isRunning){
            handler.postDelayed(runnable, 1000)
            isRunning = true

            binding.stopbtn.isEnabled = true
            binding.startbtn.isEnabled = false
            binding.resetbtn.isEnabled = true
        }
    }

    private fun stopTimer(){
        if(isRunning){
            handler.removeCallbacks(runnable)
            isRunning=false
            binding.startbtn.isEnabled=true
            binding.startbtn.text = "RESUME"
            binding.stopbtn.isEnabled = false
            binding.resetbtn.isEnabled = true
        }
    }

private fun resetTimer(){

            stopTimer()
            timerSeconds=0

            binding.timerText.text = "00:00:00"
            binding.startbtn.text = "START"
            binding.startbtn.isEnabled=true
            binding.resetbtn.isEnabled = false

    }


}