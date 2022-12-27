package com.example.project2_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_layout)

        val field1 = findViewById<TextView>(R.id.field1)

        val button_C = findViewById<TextView>(R.id.button_c)
        val button_BS = findViewById<TextView>(R.id.button_bs)
        val button_1 = findViewById<TextView>(R.id.button_1)
        val button_2 = findViewById<TextView>(R.id.button_2)

        var text1 = field1.text.toString()

        fun pressedButton(button: String, row: String) {
            if (row == "0") {
                field1.text = button
            } else {
                field1.text = field1.text.toString() + button
            }
        }

        button_C.setOnClickListener {
            field1.text = "0"
        }

        button_BS.setOnClickListener {
            if (field1.text.toString().length > 1) {
                field1.text = field1.text.toString().dropLast(1)
            } else {
                field1.text = "0"
            }
        }

        button_1.setOnClickListener {
//            field1.text.padEnd(field1.text.length, '1')

//            field1.text = field1.text.toString() + "1"
        }

        button_2.setOnClickListener {
//            field1.text = text1.padEnd(field1.text.length, '2')
//            field1.text.padEnd(field1.text.length + 1, '2')

//            field1.text = field1.text.toString() + "2"
        }
    }
}