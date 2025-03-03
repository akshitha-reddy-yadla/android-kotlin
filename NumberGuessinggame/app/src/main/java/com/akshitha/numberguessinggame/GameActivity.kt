package com.akshitha.numberguessinggame

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akshitha.numberguessinggame.databinding.ActivityGameBinding
import com.akshitha.numberguessinggame.databinding.ActivityMainBinding

class GameActivity : AppCompatActivity() {

    private lateinit var gameBinding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        var view = gameBinding.root

        setContentView(view)

        gameBinding.toolBarGame.setNavigationOnClickListener {
            finish()
        }

        when(intent.getStringExtra("number").toString()) {
            "one" -> Toast.makeText(applicationContext, "One Digit Number", Toast.LENGTH_SHORT).show()
            "two" -> Toast.makeText(applicationContext, "Two Digits Number", Toast.LENGTH_SHORT).show()
            "three" -> Toast.makeText(applicationContext, "Three Digits Number", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(applicationContext, "There is a problem", Toast.LENGTH_SHORT).show()

        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}