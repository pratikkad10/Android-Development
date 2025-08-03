package com.example.quizapp

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf("What is the capital of France?",
                                    "Which planet is known as the Red Planet?",
                                    "Who developed the Android operating system?")

    private val options = arrayOf(arrayOf(" Berlin", "Paris", "Rome"),
                                    arrayOf("Mars", "Jupiter", "Venus"),
                                    arrayOf("Apple", "Google", "Microsoft"))

    private val answers = arrayOf(1, 0, 1)

    private var currentQuestionIndex = 0
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()

        binding.option1btn.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2btn.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3btn.setOnClickListener {
            checkAnswer(2)
        }

        binding.restartbtn.setOnClickListener {
            resetQuiz()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun correctButtonColor(buttonIndex:Int){
        when(buttonIndex){
            0 -> binding.option1btn.setBackgroundColor(Color.GREEN)
            1 -> binding.option2btn.setBackgroundColor(Color.GREEN)
            2 -> binding.option3btn.setBackgroundColor(Color.GREEN)
        }

    }

    private fun wrongButtonColor(buttonIndex:Int){
        when(buttonIndex){
            0 -> binding.option1btn.setBackgroundColor(Color.RED)
            1 -> binding.option2btn.setBackgroundColor(Color.RED)
            2 -> binding.option3btn.setBackgroundColor(Color.RED)
        }

    }

    private fun resetButtonColor(){
        binding.option1btn.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2btn.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3btn.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun showScore(){
        Toast.makeText(this, "Your score: $score out of ${questions.size}", Toast.LENGTH_SHORT).show()
        binding.restartbtn.isEnabled = true
    }

    private fun displayQuestions(){
        binding.questionTxt.text = questions[currentQuestionIndex]
        binding.option1btn.text = options[currentQuestionIndex][0]
        binding.option2btn.text = options[currentQuestionIndex][1]
        binding.option3btn.text = options[currentQuestionIndex][2]
        resetButtonColor()

    }

    private fun checkAnswer(selectedAnswerIndex:Int){
        val correctAnswerIndex = answers[currentQuestionIndex]

        if(correctAnswerIndex == selectedAnswerIndex){
            score++
            correctButtonColor(selectedAnswerIndex)
        }else{
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAnswerIndex)
        }

        if(currentQuestionIndex < questions.size -1 ){
            currentQuestionIndex++
            binding.questionTxt.postDelayed({displayQuestions()}, 1000)
        }else{
            showScore()
        }
    }

    private fun resetQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestions()
        binding.restartbtn.isEnabled=false
    }


}