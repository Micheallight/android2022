package com.example.exchangerates.activities

import android.content.Intent
import com.example.exchangerates.service.ApiService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.exchangerates.R
import com.example.exchangerates.adapter.ExchangeAdapter
import com.example.exchangerates.data.Exchange
import com.example.exchangerates.db.ExchangeDb
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ExchangeAdapter.OnExchangeClickListener {

    private val apiService = ApiService.create()
    private var exchangeList = arrayListOf<Exchange>()
    private val adapter: ExchangeAdapter = ExchangeAdapter(exchangeList, this)

    private val exchangeDao by lazy { ExchangeDb.getDatabase(this).exchangeDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcMain.adapter = adapter

        getDataFromApi()

        btnSaveAll.setOnClickListener { saveAllToDb() }

        btnSaved.setOnClickListener { startActivity(Intent(this, SavedActivity::class.java)) }
    }

    private fun saveAllToDb() {
        exchangeDao.saveAll(exchangeList)
        Toast.makeText(this, "Data has been saved to db", Toast.LENGTH_SHORT).show()
    }

    private fun getDataFromApi() {
        val call = apiService.getPosts()
        call.enqueue(object: Callback<List<Exchange>> {
            override fun onFailure(call: Call<List<Exchange>>, t: Throwable) {
                Log.d("!!!",t.toString())
            }
            override fun onResponse(call: Call<List<Exchange>>, response: Response<List<Exchange>>) {
                exchangeList.clear()
                exchangeList.addAll(response.body()!!)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onExchangeClick(position: Int) {
        val exchangeInfoIntent = Intent(this, ExchangeInfoActivity::class.java)
        val exchange = exchangeList[position]
        exchangeInfoIntent.putExtra("exchange", exchange)
        startActivity(exchangeInfoIntent)
    }
}