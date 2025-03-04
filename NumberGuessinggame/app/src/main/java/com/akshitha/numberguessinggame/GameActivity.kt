package com.akshitha.numberguessinggame

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.akshitha.numberguessinggame.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var gameBinding: ActivityGameBinding

    private var randomNumber : Int = -1
    private var remainingRight = 8;

    private var steps = 0
    private var guesses = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        var view = gameBinding.root

        setContentView(view)

        gameBinding.toolBarGame.setNavigationOnClickListener {
            finish()
        }

        gameBinding.textViewHint.visibility = View.INVISIBLE
        gameBinding.textViewLastGuess.visibility = View.INVISIBLE
        gameBinding.textViewRight.visibility = View.INVISIBLE

        when(intent.getStringExtra("number").toString()) {
            "one" -> {
                gameBinding.textViewInfo.text = "Guess a number between 0-9"
                randomNumber = Random.nextInt(0, 10)
                remainingRight = 3
            }
            "two" -> {
                gameBinding.textViewInfo.text = "Guess a number between 10-99"
                randomNumber = Random.nextInt(10, 100)
                remainingRight = 8
            }
            "three" -> {
                gameBinding.textViewInfo.text = "Guess a number between 100-999"
                randomNumber = Random.nextInt(100, 1000)
                remainingRight = 13
            }
            else -> Toast.makeText(applicationContext, "There is a problem", Toast.LENGTH_SHORT).show()
        }

        gameBinding.buttonConfirm.setOnClickListener {
            gameLogin(randomNumber)
        }
    }

    private fun gameLogin (randomNumber: Int) {
        val guess: String = gameBinding.editTextNumber.text.toString()

        if(guess == "") {
            Toast.makeText(applicationContext, "Please enter a guess", Toast.LENGTH_LONG).show()
        }else {
            try {
                val userGuess = guess.toInt()
                if(randomNumber == -1) {
                    Toast.makeText(applicationContext, "Random Number cannot be -1, Please try again", Toast.LENGTH_LONG).show()
                }else {
                    steps++
                    guesses.add(userGuess)

                    if(randomNumber == userGuess) {
                        createDialogMessage()
//                        gameBinding.textViewHint.text = "Congratulations, The number in my mind was $randomNumber"
                        // dialog message
                    }else {
                        remainingRight--

                        if(remainingRight == 0) {
                            createDialogMessage()
//                            gameBinding.textViewHint.text = "Sorry, Game over"
                            //dialog message
                        }else {
                            if(userGuess < randomNumber) {
                                gameBinding.textViewHint.text = "Increase your guess"
                            }else {
                                gameBinding.textViewHint.text = "Decrease your guess"
                            }
                        }
                    }
                    gameBinding.textViewRight.text = "Your remaining right is $remainingRight"
                    gameBinding.textViewLastGuess.text = "Your last right is $userGuess"


                    gameBinding.textViewHint.visibility = View.VISIBLE
                    gameBinding.textViewLastGuess.visibility = View.VISIBLE
                    gameBinding.textViewRight.visibility = View.VISIBLE

                    gameBinding.editTextNumber.setText("")
                }
            }catch (e: Exception) {
                Toast.makeText(applicationContext, "Please enter a valid guess", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createDialogMessage() {
        val dialogBox = AlertDialog.Builder(this@GameActivity, )
        dialogBox.setTitle("Number Guessing Game")
        if(remainingRight > 0) {
            dialogBox.setMessage("\tCongratulations, The number in my mind was $randomNumber" +
                    "\n\n\tYou know my number in $steps " +
            "\n\n\t Your guesses: $guesses" +
            "\n\n\t Do you want to play again ?")
        }else {
            dialogBox.setMessage("\tSorry, your right to guess is over" +
                    "\n\n\tThe number in my mind was $randomNumber" +
                    "\n\n\t Your guesses: $guesses" +
                    "\n\n\t Do you want to play again ?")
        }
        dialogBox.setCancelable(false)
        dialogBox.setNegativeButton("No", OnClickListener { dialogInterface, i ->
            finishAffinity()
        })

        dialogBox.setPositiveButton("YES",OnClickListener { dialogInterface, i ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        dialogBox.create().show()
    }
}