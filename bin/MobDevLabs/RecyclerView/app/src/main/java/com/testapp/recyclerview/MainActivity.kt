package com.testapp.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val persons = mutableListOf<Person>()
    lateinit var personsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        persons.add(Person("John", "White", 27, R.drawable.p1))
        persons.add(Person("Alex", "Smile", 29, R.drawable.p2))
        persons.add(Person("Nike", "Splender", 45, R.drawable.p3))
        persons.add(Person("Dima", "Vechorka", 36, R.drawable.p4))
        persons.add(Person("Andrew", "Wize", 19, R.drawable.p5))

        personsList = findViewById(R.id.recyclerView)
        personsList.layoutManager = LinearLayoutManager(this)
        personsList.adapter = PersonAdapter(persons)

    }
}