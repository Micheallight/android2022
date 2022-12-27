package com.michaellight.mycalculatorv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
	val listOfValues = arrayOf<Array<String>>(
		arrayOf<String>("C", "back", "+/-", "/"),
		arrayOf<String>("7", "8", "9", "*"),
		arrayOf<String>("4", "5", "6", "-"),
		arrayOf<String>("1", "2", "3", "+"),
		arrayOf<String>("0", "00", ".", "=")
	)

	val buttons = mutableListOf<com.michaellight.mycalculatorv1.Button>()
	lateinit var btnList: LinearLayout

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		buttons.add(Button(listOfValues[0][0]))

		btnList = findViewById(R.id.ButtonTable)
		btnList.
	}
}