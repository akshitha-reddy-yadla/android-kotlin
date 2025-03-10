package com.akshitha.calculator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akshitha.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var number: String? = null

    private var firstNumber: Double = 0.0;
    private var lastNumber: Double = 0.0;

    private var status: String? = null
    private var operator: Boolean = false

    private val myFormatter = DecimalFormat("######.######")

    private var history: String = ""
    private var correctResult: String = ""

    private var dotControl: Boolean = true

    private var btnEqualsControl: Boolean = false

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        val view = mainBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainBinding.textViewResult.text = "0"

        mainBinding.btnZero.setOnClickListener {
            onNumberClicked("0")
        }

        mainBinding.btnOne.setOnClickListener {
            onNumberClicked("1")
        }

        mainBinding.btnTwo.setOnClickListener {
            onNumberClicked("2")
        }

        mainBinding.btnThree.setOnClickListener {
            onNumberClicked("3")
        }

        mainBinding.btnFour.setOnClickListener {
            onNumberClicked("4")
        }

        mainBinding.btnFive.setOnClickListener {
            onNumberClicked("5")
        }

        mainBinding.btnSix.setOnClickListener {
            onNumberClicked("6")
        }

        mainBinding.btnSeven.setOnClickListener {
            onNumberClicked("7")
        }

        mainBinding.btnEight.setOnClickListener {
            onNumberClicked("8")
        }

        mainBinding.btnNine.setOnClickListener {
            onNumberClicked("9")
        }

        mainBinding.btnAc.setOnClickListener {
            onBtnAcClick()
        }

        mainBinding.btnDel.setOnClickListener {
            number?.let {
                if (it.length == 1) {
                    onBtnAcClick()
                } else {
                    number = it.substring(0, it.length - 1)
                    mainBinding.textViewResult.text = number
                    dotControl = !number!!.contains(".")
                }
            }
        }

        mainBinding.btnDivide.setOnClickListener {

            history = mainBinding.textViewHistory.text.toString()
            correctResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(correctResult).plus("/")

            if (operator) {
                when (status) {
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()
                }
            }

            status = "division"
            operator = false
            number = null
            dotControl = true
        }

        mainBinding.btnMultiply.setOnClickListener {
            history = mainBinding.textViewHistory.text.toString()
            correctResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(correctResult).plus("*")

            if (operator) {
                when (status) {
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()
                }
            }

            status = "multiplication"
            operator = false
            number = null
            dotControl = true
        }

        mainBinding.btnMinus.setOnClickListener {
            history = mainBinding.textViewHistory.text.toString()
            correctResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(correctResult).plus("-")

            if (operator) {
                when (status) {
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()
                }
            }

            status = "subtraction"
            operator = false
            number = null
            dotControl = true
        }

        mainBinding.btnPlus.setOnClickListener {
            history = mainBinding.textViewHistory.text.toString()
            correctResult = mainBinding.textViewResult.text.toString()
            mainBinding.textViewHistory.text = history.plus(correctResult).plus("+")

            if (operator) {
                when (status) {
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()
                }
            }

            status = "addition"
            operator = false
            number = null
            dotControl = true
        }

        mainBinding.btnEqual.setOnClickListener {
            history = mainBinding.textViewHistory.text.toString()
            correctResult = mainBinding.textViewResult.text.toString()

            if (operator) {
                when (status) {
                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = mainBinding.textViewResult.text.toString().toDouble()
                }
                mainBinding.textViewHistory.text = history.plus(correctResult).plus("=")
                    .plus(mainBinding.textViewResult.text.toString())

            }
            operator = false
            dotControl = true
            btnEqualsControl = true
        }

        mainBinding.btnDot.setOnClickListener {
            if (dotControl) {
                number = if (number == null) {
                    "0."
                } else if (btnEqualsControl) {
                    if (mainBinding.textViewResult.text.toString().contains(".")) {
                        mainBinding.textViewResult.text.toString()
                    } else {
                        mainBinding.textViewResult.text.toString().plus(".")
                    }
                } else {
                    "$number."
                }
                mainBinding.textViewResult.text = number
            }
            dotControl = false
        }

        mainBinding.toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId) {

                R.id.settings_item -> {
                    val intent = Intent(this@MainActivity, ChangeThemeActivity::class.java)
                    startActivity(intent)
                    return@setOnMenuItemClickListener true
                }

                else -> return@setOnMenuItemClickListener false
            }

        }
    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = this.getSharedPreferences("Dark Theme", Context.MODE_PRIVATE)

        val isDarkMode = sharedPreferences.getBoolean("switch", false)

        if(isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onPause() {
        super.onPause()

        sharedPreferences = this.getSharedPreferences("calculations", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        val resultToSave = mainBinding.textViewResult.text.toString()
        val historyToSave = mainBinding.textViewHistory.text.toString()
        val numberToSave = number
        val statusToSave = status
        val operatorToSave = operator
        val dotToSave = dotControl
        val equalToSave = btnEqualsControl
        val firstNumberToSave = firstNumber.toString()
        val lastNumberToSave = lastNumber.toString()

        editor.putString("result", resultToSave);
        editor.putString("history", historyToSave)
        editor.putString("number", numberToSave)
        editor.putString("status", statusToSave)
        editor.putBoolean("operator", operatorToSave)
        editor.putBoolean("dot", dotToSave)
        editor.putBoolean("equal", equalToSave)
        editor.putString("firstNumber", firstNumberToSave)
        editor.putString("lastNumber", lastNumberToSave)

        editor.apply()
    }

    override fun onStart() {
        super.onStart()

        sharedPreferences = this.getSharedPreferences("calculations", Context.MODE_PRIVATE)

        mainBinding.textViewResult.text = sharedPreferences.getString("result", "0");
        mainBinding.textViewHistory.text = sharedPreferences.getString("history", "");

        number = sharedPreferences.getString("number", null)
        status = sharedPreferences.getString("status", null)
        operator = sharedPreferences.getBoolean("operator", false)
        dotControl = sharedPreferences.getBoolean("dot", true)
        btnEqualsControl = sharedPreferences.getBoolean("equal", false)
        firstNumber = sharedPreferences.getString("firstNumber", "0.0")!!.toDouble()
        lastNumber = sharedPreferences.getString("lastNumber", "0.0")!!.toDouble()
    }

    private fun onBtnAcClick() {
        number = null
        status = null
        mainBinding.textViewResult.text = "0"
        mainBinding.textViewHistory.text = ""
        firstNumber = 0.0
        lastNumber = 0.0
        dotControl = true
        btnEqualsControl = false
    }

    private fun onNumberClicked(clickedNumber: String) {
        if (number == null) {
            number = clickedNumber
        } else if (btnEqualsControl) {
            number = if (dotControl) {
                clickedNumber
            } else {
                mainBinding.textViewResult.text.toString().plus(clickedNumber)
            }

            firstNumber = number!!.toDouble()
            lastNumber = 0.0
            status = null
            mainBinding.textViewHistory.text = number

        } else {
            number += clickedNumber
        }
        mainBinding.textViewResult.text = number

        operator = true
        btnEqualsControl = false
    }

    private fun plus() {
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()
        firstNumber += lastNumber
        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }

    private fun minus() {
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()
        firstNumber -= lastNumber
        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }

    private fun multiply() {
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()
        firstNumber *= lastNumber
        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }

    private fun divide() {
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()

        if (lastNumber == 0.0) {
            Toast.makeText(applicationContext, "The divisor cannot be zero", Toast.LENGTH_LONG)
                .show()
        } else {
            firstNumber /= lastNumber
            mainBinding.textViewResult.text = myFormatter.format(firstNumber)
        }
    }
}