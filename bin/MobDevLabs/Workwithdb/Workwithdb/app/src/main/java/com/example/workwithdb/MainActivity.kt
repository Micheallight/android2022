package com.example.workwithdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val personDao = db.personDao()

        btnRead.setOnClickListener(){
            persons.clear()
            persons.addAll(personDao.getAll())
            et_persons.setText(persons.toString())
        }

        btnDeleteAll.setOnClickListener(){
            //persons.clear()
            personDao.deleteAll()
            et_persons.setText("All persons delete")
        }

        btnDeleteById.setOnClickListener(){
            val nameForDelete = et_name.text.toString()
            personDao.deleteByName(nameForDelete)
            et_persons.setText("$nameForDelete delete")
        }

        btnAdd.setOnClickListener(){
            val newPerson = Person(et_name.text.toString(), et_secondName.text.toString(), et_age.text.toString().toInt())
            personDao.addNew(newPerson)
            et_persons.setText("Person ${newPerson.name} delete")
        }

    }
}