package com.testapp.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class ExchangeSavedActivity : AppCompatActivity() {

    var exchangeDB = listOf<Exchange>()
    lateinit var exchangeSavedRecyclerView: RecyclerView
    val REQUEST_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_saved)

        val db = Room.databaseBuilder(
            applicationContext,
            ExchangeSavedDB::class.java, "exchange_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val exchangeSavedDao = db.exchangeSavedDao()

        exchangeDB = exchangeSavedDao.getAll()
        exchangeSavedRecyclerView = findViewById(R.id.recyclerViewSavedlist)
        exchangeSavedRecyclerView.layoutManager = LinearLayoutManager(this)
        exchangeSavedRecyclerView.adapter = ExchangeSavedAdapter(exchangeDB,this)
    }

    fun itemClick(position: Int){
        val intent = Intent(this, ExchangeActivity::class.java)
        intent.putExtra("product", exchangeDB[position])
        startActivityForResult(intent, REQUEST_CODE)
    }

    fun deleteItemFromWishList(position: Int){
        val db = Room.databaseBuilder(
            applicationContext,
            ExchangeSavedDB::class.java, "exchange_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val exchangeSavedDao = db.exchangeSavedDao()

        val expectedExchanges = exchangeSavedDao.findByPrimaryKey(exchangeDB[position].id)
        exchangeSavedDao.deleteByName(expectedExchanges[0].street)

        exchangeDB = exchangeSavedDao.getAll()
        exchangeSavedRecyclerView = findViewById(R.id.recyclerViewSavedlist)
        exchangeSavedRecyclerView.layoutManager = LinearLayoutManager(this)
        exchangeSavedRecyclerView.adapter = ExchangeSavedAdapter(exchangeDB,this)
    }
}