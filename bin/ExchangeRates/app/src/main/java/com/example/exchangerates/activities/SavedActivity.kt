package com.example.exchangerates.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.exchangerates.R
import com.example.exchangerates.adapter.ExchangeSavedAdapter
import com.example.exchangerates.data.Exchange
import com.example.exchangerates.db.ExchangeDao
import com.example.exchangerates.db.ExchangeDb
import kotlinx.android.synthetic.main.activity_saved.*

class SavedActivity : AppCompatActivity(),
    ExchangeSavedAdapter.OnExchangeClickListener, ExchangeSavedAdapter.OnExchangeDeleteClickListener {

    private var exchangeList = arrayListOf<Exchange>()
    private val adapter: ExchangeSavedAdapter = ExchangeSavedAdapter(exchangeList, this,this)

    private val exchangeDao by lazy { ExchangeDb.getDatabase(this).exchangeDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        rcSaved.adapter = adapter

        readDataFromDb()

        btnSearch.setOnClickListener { searchByCity() }
        btnDeleteAll.setOnClickListener { deleteAllFromDb() }
    }

    private fun deleteAllFromDb() {
        exchangeDao.deleteAll()
        readDataFromDb()
    }

    private fun searchByCity() {
        val city = edSearch.text.toString().trim()

        if(city.isNotEmpty()) {
            exchangeList.clear()
            exchangeList.addAll(exchangeDao.findByCity(city))
            adapter.notifyDataSetChanged()
        }
    }

    private fun readDataFromDb() {
        exchangeList.clear()
        exchangeList.addAll(exchangeDao.getAll())
        adapter.notifyDataSetChanged()

        for(i in exchangeList.indices) {
            Log.d("my_log", exchangeList[i].toString())
            if(i > 5)
                break
        }

        if(exchangeList.isEmpty())
            Toast.makeText(applicationContext, "Empty DB", Toast.LENGTH_SHORT).show()
    }

    override fun onExchangeClick(position: Int) {
        val exchangeInfoIntent = Intent(this, ExchangeInfoActivity::class.java)
        val exchange = exchangeList[position]
        exchangeInfoIntent.putExtra("exchange", exchange)
        startActivity(exchangeInfoIntent)
    }

    override fun onExchangeDeleteClick(position: Int) {
        val exchange = exchangeList[position]
        exchangeDao.delete(exchange)
        readDataFromDb()
        Toast.makeText(this, "Item deleted from db", Toast.LENGTH_SHORT).show()
    }
}