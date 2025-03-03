package com.akshitha.numberguessinggame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.akshitha.numberguessinggame.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)

        val view = splashBinding.root
        enableEdgeToEdge()
        setContentView(view)

        splashBinding.imageViewSplash.animation = AnimationUtils.loadAnimation(this, R.anim.image_animation)
        splashBinding.textViewSplash.animation = AnimationUtils.loadAnimation(this, R.anim.text_animation)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object: Runnable {
            override fun run() {
               val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent);
                finish()
            }
        }, 5000)
    }
}