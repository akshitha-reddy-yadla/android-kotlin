package com.akshitha.calculator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akshitha.calculator.databinding.ActivityChangeThemeBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class ChangeThemeActivity : AppCompatActivity() {

    private lateinit var switchBinding: ActivityChangeThemeBinding

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        switchBinding = ActivityChangeThemeBinding.inflate(layoutInflater)
        val view = switchBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val function: (View) -> Unit = {
            finish()
        }
        switchBinding.toolbar2.setNavigationOnClickListener(function)

        switchBinding.mySwitch.setOnCheckedChangeListener { buttonView, isChecked ->

            sharedPreferences = this.getSharedPreferences("Dark Theme", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            Log.i("theme 1", isChecked.toString())

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("switch", true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("switch", false)
            }
            editor.apply()
        }
    }

    override fun onResume() {

        super.onResume()
        sharedPreferences = this.getSharedPreferences("Dark Theme", Context.MODE_PRIVATE)


        val isDark = sharedPreferences.getBoolean("switch", false)

        Log.i("theme 2", isDark.toString())

        switchBinding.mySwitch.isChecked = isDark
    }
}