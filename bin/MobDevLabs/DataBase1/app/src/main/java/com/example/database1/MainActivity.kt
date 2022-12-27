package com.example.database1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	val persons = mutableListOf<Person>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val db = Room.databaseBuilder(
			applicationContext,
			PersonDB::class.java, "person_db"
			)
			.allowMainThreadQueries()
			.build()

		val personDao = db.personDao()

		btnReadAll.setOnClickListener {
			persons.addAll(personDao.getAll())
			allpersons.setText(persons.toString())

		}

		btnDeleteAll.setOnClickListener {
			persons.clear()
			personDao.deleteAll()
			allpersons.setText(persons.toString())
		}

		btnDeleteByName.setOnClickListener {
			val name = "Ivan"
			personDao.deleteByName(name)
			allpersons.setText("$name deleted")
		}

		btnAddNew.setOnClickListener {
			val newPerson = Person(
				editInsertName.text.toString(),
				editInsertSecondName.text.toString(),
				editInsertAge.text.toString().toInt()
			)
			personDao.addNew(newPerson)
			allpersons.setText("${newPerson.name} ${newPerson.secondName} in age ${newPerson.age} comes to us!")
		}
	}
}