package com.example.exchangerates

import android.content.Intent
import com.example.exchangerates.json.ApiService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.exchangerates.db.DeleteDataDialogFragment
import com.example.exchangerates.db.ExchangeDao
import com.example.exchangerates.db.ExchangeDb
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity :
    AppCompatActivity(), DeleteDataDialogFragment.OnDismissDialogListener, ExchangeAdapter.OnExchangeClickListener {

    private val apiService = ApiService.create()
    private var exchangeList = arrayListOf<Exchange>()
    private val adapter: ExchangeAdapter = ExchangeAdapter(exchangeList, this)
    private var db: ExchangeDb? = null
    private var exchangeDao: ExchangeDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, ExchangeDb::class.java, "exchange_db")
            .allowMainThreadQueries()
            .build()
        exchangeDao = db!!.exchangeDao()
        rc.adapter = adapter

        btnSave.setOnClickListener {
            saveDataToDb()
        }

        btnRead.setOnClickListener {
            readDataFromDb()
        }

        btnDelete.setOnClickListener {
            deleteDataFromDb()
        }

        btnSearch.setOnClickListener {
            searchByCity()
        }
    }

    private fun searchByCity() {
        val city = edSearch.text.toString().trim()

        if(city.isNotEmpty()) {
            exchangeList.clear()
            exchangeList.addAll(exchangeDao!!.getByCity(city))
            adapter.notifyDataSetChanged()
        }
    }

    private fun saveDataToDb() {
        val call = apiService.getPosts()
        call.enqueue(object: Callback<List<Exchange>> {
            override fun onFailure(call: Call<List<Exchange>>, t: Throwable) {
                Log.d("!!!",t.toString())
            }
            override fun onResponse(call: Call<List<Exchange>>, response: Response<List<Exchange>>) {
                if(exchangeDao!!.getAll().isNotEmpty()) exchangeDao!!.deleteAll()
                exchangeDao!!.saveAll(response.body()!!)

                Toast.makeText(applicationContext,"Data has been saved to DB", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun readDataFromDb() {
        exchangeList.clear()
        exchangeList.addAll(exchangeDao!!.getAll())
        adapter.notifyDataSetChanged()

        if(exchangeList.isEmpty())
            Toast.makeText(applicationContext, "Empty DB", Toast.LENGTH_SHORT).show()
    }

    private fun deleteDataFromDb() {
        if(exchangeDao!!.getAll().isEmpty()) {
            Toast.makeText(applicationContext, "Empty DB", Toast.LENGTH_SHORT).show()
            return
        }

        val dialog = DeleteDataDialogFragment(this)
        val args = Bundle()

        args.putSerializable("exchangeDao", exchangeDao)
        dialog.arguments = args
        dialog.show(supportFragmentManager,"dialog")

        Log.d("dialog","showing dialog")
    }

    override fun onDismiss() {
        if(exchangeDao!!.getAll().isEmpty()) {
            exchangeList.clear()
            adapter.notifyDataSetChanged()
        }
        Toast.makeText(applicationContext, "Data has been deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onExchangeClick(exchange: Exchange, position: Int) {
        val intent = Intent(this, ExchangeInfoActivity::class.java)

        val exchangeDto = ExchangeDto(
            exchange.name_type, exchange.name, exchange.street_type, exchange.street, exchange.home_number)

        intent.putExtra("exchangeDto", exchangeDto)

        startActivity(intent)
    }
}