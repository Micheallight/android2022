package com.example.perconrecycleview2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class InputActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_input)
		val editName = findViewById<EditText>(R.id.editName)
		val editSecondName = findViewById<EditText>(R.id.editSecondName)
		val editAge = findViewById<EditText>(R.id.editAge)
		val btnAdd = findViewById<Button>(R.id.btbAdd)

		btnAdd.setOnClickListener(){
			val resultIntent = Intent()
			intent.putExtra("name", editName.text.toString())
			intent.putExtra("secondname", editSecondName.text.toString())
			intent.putExtra("age", editAge.text.toString().toInt())

			setResult(RESULT_OK, resultIntent)
			finish()
		}

	}
}