package com.testapp.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val apiService = ApiService.create()
    var exchanges = listOf<Exchange>()
    var exchangesFilteredByCity = listOf<Exchange>()
    val REQUEST_CODE = 10
    lateinit var exchangeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getExchange()

        val spinner = findViewById<Spinner>(R.id.citySpinner)
        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val item = parent.getItemAtPosition(position) as String
                    Toast.makeText(this@MainActivity, item,Toast.LENGTH_LONG).show()
                    exchangesFilteredByCity = exchanges.filter{it.name.equals(item)}
                    //tv.text = exchangesFilteredByCity.toString()
                    exchangeRecyclerView = findViewById(R.id.recyclerView)
                    exchangeRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    exchangeRecyclerView.adapter = ExchangeAdapter(exchangesFilteredByCity,this@MainActivity)

                    Log.d(Const.LOG_TAG, exchanges.toString())
                    Log.d(Const.LOG_TAG, exchangesFilteredByCity.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinner.onItemSelectedListener = itemSelectedListener

        btnSavedlist.setOnClickListener(){
            val intent = Intent(this, ExchangeSavedActivity::class.java)
            startActivity(intent)
        }
    }

    fun itemClick(position: Int){
        val intent = Intent(this, ExchangeActivity::class.java)
        intent.putExtra("product", exchangesFilteredByCity[position])
        startActivityForResult(intent, REQUEST_CODE)
    }

    fun addItemToSavedList(position: Int){
        val db = Room.databaseBuilder(
            applicationContext,
            ExchangeSavedDB::class.java, "exchange_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val exchangeSavedDao = db.exchangeSavedDao()

        if (findById(exchangesFilteredByCity[position].id)) {
            exchangeSavedDao.addNew(exchangesFilteredByCity[position])
        }
    }

    fun getExchange() {
        val call = apiService.getExchange()

        call.enqueue(object: Callback<List<Exchange>> {

            override fun onFailure(call: Call<List<Exchange>>, t: Throwable) {
                Log.d("!!!", t.toString())
            }

            override fun onResponse(
                call: Call<List<Exchange>>,
                response: Response<List<Exchange>>
            ) {
                Log.d(Const.LOG_TAG, "onResponce()")

                exchanges = response.body()!!
                //tv.text = exchanges.toString()
                exchangesFilteredByCity = exchanges.filter{it.name.equals("Минск")}
                //tv.text = exchangesFilteredByCity.toString()
                exchangeRecyclerView = findViewById(R.id.recyclerView)
                exchangeRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                exchangeRecyclerView.adapter = ExchangeAdapter(exchangesFilteredByCity,this@MainActivity)

                Log.d(Const.LOG_TAG, exchanges.toString())
                Log.d(Const.LOG_TAG, exchangesFilteredByCity.toString())
            }
        })
    }

    fun findById(num: Int): Boolean{
        val db = Room.databaseBuilder(
            applicationContext,
            ExchangeSavedDB::class.java, "exchange_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()

        val exchangeSavedDao = db.exchangeSavedDao()

        val expectedCarsFromDB = exchangeSavedDao.findByPrimaryKey(num)

        return expectedCarsFromDB.isEmpty()
    }

}