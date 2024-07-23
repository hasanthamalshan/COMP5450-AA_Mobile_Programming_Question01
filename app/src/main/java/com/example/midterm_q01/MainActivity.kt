package com.example.midterm_q01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.midterm_q01.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val num1Input = findViewById<EditText>(R.id.num1Input)
        val num2Input = findViewById<EditText>(R.id.num2Input)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val button = findViewById<Button>(R.id.button)
        val answerText = findViewById<TextView>(R.id.answer)

        // Populate spinner with operation options
        val operations = arrayOf("Add", "Subtract", "Multiply", "Divide")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button.setOnClickListener {
            // Get input values
            val num1 = num1Input.text.toString().toDoubleOrNull()
            val num2 = num2Input.text.toString().toDoubleOrNull()
            val operation = spinner.selectedItem.toString()

            // Check if input values are valid
            if (num1 != null && num2 != null) {
                // Perform calculation based on selected operation
                val result = when (operation) {
                    "Add" -> num1 + num2
                    "Subtract" -> num1 - num2
                    "Multiply" -> num1 * num2
                    "Divide" -> {
                        if (num2 != 0.0) {
                            num1 / num2
                        } else {
                            "Error: Division by zero"
                        }
                    }
                    else -> "Invalid Operation"
                }
                answerText.text = result.toString()
            } else {
                answerText.text = "Please enter valid numbers"
            }

        }
    }
}