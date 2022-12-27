package com.example.perconrecycleview2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
	val persons = mutableListOf<Person>()
	lateinit var personsList: RecyclerView


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		persons.add(Person("John","White",27,R.drawable.p1))
		persons.add(Person("Alex","Smile",29,R.drawable.p2))
		persons.add(Person("Nike","Splender",45,R.drawable.p3))
		persons.add(Person("Dima","Vechorka",36,R.drawable.p4))
		persons.add(Person("Andrew","Wize",19,R.drawable.p5))

		personsList = findViewById(R.id.recyclerView)
		personsList.layoutManager = LinearLayoutManager(this)
		personsList.adapter = PersonAdapter(persons,this)

		val btnAdd = findViewById<Button>(R.id.btbAdd)


		btnAdd.setOnClickListener {
//			persons.add(Person("New name","Family name",29,R.drawable.p6))
//			personsList.adapter?.notifyItemInserted(persons.size - 1)
			val intent = Intent(this, InputActivity::class.java)
			startActivity(intent)
		}

	}
	fun itemClick(position: Int){
		Toast.makeText(this,"Hello, ${persons[position].name}",Toast.LENGTH_LONG).show()
	}
	fun removeItem(position: Int){
		persons.removeAt(position)
		personsList.adapter?.notifyItemRemoved(position)
	}

}