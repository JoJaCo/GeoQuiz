package com.bignerdramch.andriod.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView
    private var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true)
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        // assigning the buttons to variables
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.pre_button)
        questionTextView = findViewById(R.id.question_text_view)

        //true button is selected
        trueButton.setOnClickListener{View ->
         checkAnswer(true)


        }
        //false button is selected
        falseButton.setOnClickListener{View ->
            checkAnswer(false)

        }
        //nextButton is selected
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1)% questionBank.size
            updateQuestion()
        }
        //on create first question is shown
        updateQuestion()
        //pre button is selected
        prevButton.setOnClickListener {
            if (currentIndex != 0) {
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()
            }else if (currentIndex == 0)
                currentIndex = 5
                updateQuestion()

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    //updates the question 
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show()
    }
}