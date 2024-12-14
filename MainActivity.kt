
package com.example.scientificcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentInput: String = ""
    private var operator: String? = null
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        // Number buttons
        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )
        buttons.forEachIndexed { index, id ->
            findViewById<Button>(id).setOnClickListener { appendToInput(index.toString()) }
        }

        // Operator buttons
        findViewById<Button>(R.id.buttonAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { setOperator("/") }

        // Function buttons
        findViewById<Button>(R.id.buttonClear).setOnClickListener { clearInput() }
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { calculateResult() }
    }

    private fun appendToInput(value: String) {
        currentInput += value
        display.text = currentInput
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            operator = op
            result = currentInput.toDouble()
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operator != null) {
            val secondOperand = currentInput.toDouble()
            result = when (operator) {
                "+" -> result + secondOperand
                "-" -> result - secondOperand
                "*" -> result * secondOperand
                "/" -> if (secondOperand != 0.0) result / secondOperand else Double.NaN
                else -> result
            }
            display.text = result.toString()
            currentInput = ""
            operator = null
        }
    }

    private fun clearInput() {
        currentInput = ""
        operator = null
        result = 0.0
        display.text = "0"
    }
}
