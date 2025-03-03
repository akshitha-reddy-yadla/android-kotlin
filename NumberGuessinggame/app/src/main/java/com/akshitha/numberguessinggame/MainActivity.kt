package com.akshitha.numberguessinggame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.akshitha.numberguessinggame.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        var view = mainBinding.root
        setContentView(view)

        mainBinding.buttonStart.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)

            println()
            if(
                !mainBinding.radioButtonOne.isChecked &&
                !mainBinding.radioButtonTwo.isChecked &&
                !mainBinding.radioButtonThree.isChecked) {
                Snackbar.make(mainBinding.constraintLayout, "Please select a number of digits", Snackbar.LENGTH_LONG).show()
            }else {

                if(mainBinding.radioButtonOne.isChecked) {
                    intent.putExtra("number", "one")
                }else if(mainBinding.radioButtonTwo.isChecked) {
                    intent.putExtra("number", "two")
                }else {
                    intent.putExtra("number", "three")
                }
                startActivity(intent)
            }
        }
    }
}