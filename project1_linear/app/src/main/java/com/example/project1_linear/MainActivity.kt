package com.example.project1_linear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val persons = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_layout)

        persons.add(Person("John", "Smith", 25, R.drawable.p1))
        persons.add(Person("Jack", "Kent", 32, R.drawable.p2))
        persons.add(Person("Sam", "Red", 19, R.drawable.p3))
        persons.add(Person("Dean", "Rock", 42, R.drawable.p4))
        persons.add(Person("Carl", "Loon", 65, R.drawable.p5))



//        val btn1 = findViewById<Button>(R.id.button1)
//        val tw1 = findViewById<TextView>(R.id.block1)
//        val tw2 = findViewById<TextView>(R.id.block2)
//        val f1 = findViewById<EditText>(R.id.field1)
//
//        var num1 = f1.text.toString().toInt()
//
//        btn1.setOnClickListener {
//            num1 = f1.text.toString().toInt()
//
//            tw1.text = "Your social credits: ${num1++}"
//
//            var iterator = 1
//
//            for (a in 1  until num1) {
//                iterator *= a
//                tw2.text = "$iterator"
//            }
//
//        }
    }
}