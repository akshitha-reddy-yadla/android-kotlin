package com.example.helloworld

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onBtnClick(view: View) {
        var fullNameView: TextView = findViewById(R.id.txtFirstName)
        var lastNameView: TextView = findViewById(R.id.txtLastName)
        var emailView: TextView = findViewById(R.id.txtEmail)


        var editFirstNameTxt: EditText = findViewById(R.id.editFirstName)
        var editLastNameTxt: EditText = findViewById(R.id.editLastName)
        var editEmailTxt: EditText = findViewById(R.id.editEmail)

        fullNameView.setText("First name " + editFirstNameTxt.text)
        lastNameView.setText("Last name " + editLastNameTxt.text)
        emailView.setText("Email " + editEmailTxt.text)

    }
}