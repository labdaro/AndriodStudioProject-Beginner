package com.example.timefight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText

class MainActivity : AppCompatActivity() {
   internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftview: TextView
    internal  var score: Int = 0
    internal var gameStarted= false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long=60000
    internal val countDownIntterval: Long=1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tapMeButton = findViewById<Button>(R.id.tap_me_button)
        gameScoreTextView= findViewById<TextView>(R.id.game_score_text_view)
        timeLeftview= findViewById<TextView>(R.id.time_left_text_view)
        resetGame()
        tapMeButton.setOnClickListener{view ->
            incrementScore()

        }

    }
    private fun resetGame()
    {
        score=0
        gameScoreTextView.text= getString(R.string.your_score,score.toString())
        var initialTimeLeft= initialCountDown /1000
        timeLeftview.text= getString(R.string.time_left,initialTimeLeft.toString())
        countDownTimer =object : CountDownTimer(initialCountDown, countDownIntterval)
        {
            override fun onTick(millisUnitFinished:Long)
            {
                val timeLeft= millisUnitFinished/1000
                timeLeftview.text= getString(R.string.time_left,timeLeft.toString())
            }

            override fun onFinish() {
                endGame()

            }

        }
        gameStarted= false

    }
    private fun startGame()
    {
        countDownTimer.start()
        gameStarted= true
    }
    private fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message, score.toString()), Toast.LENGTH_SHORT)
        resetGame()
    }
    private fun incrementScore()
    {   if (!gameStarted)
    {
        startGame()
    }

        score +=1
        val newScore= getString(R.string.your_score,score.toString())
        gameScoreTextView.text=newScore
    }

}
