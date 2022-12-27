package com.example.myexchange

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var rateList = listOf<Rate>()
    private val REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getExchange()
    }

    fun setValue(rateList: List<Rate>) {
        var recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler.adapter = Adapter(rateList, this@MainActivity)
    }

    fun getExchange() {

        RetrofitInstance.api.getExchange().enqueue(object : Callback<Exchange> {
            override fun onResponse(call: Call<Exchange>, response: Response<Exchange>) {
                if (response.body() != null) {
                    rateList = response.body()!!.rates
                    Log.d("Exchange: ", rateList.toString())
                    setValue(rateList)
                }
            }

            override fun onFailure(call: Call<Exchange>, t: Throwable) {
                Log.d("Error, no exchange!", t.message.toString())
            }
        })
    }

    fun itemClick(position: Int) {
        val intent = Intent(this, ExchangeActivity::class.java)
        intent.putExtra("product", rateList[position])
        startActivityForResult(intent, REQUEST_CODE)
    }

}