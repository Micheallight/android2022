package com.michaellight.exchangeratesv1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaellight.exchangeratesv1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.internal.connection.Exchange
import java.io.IOException

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	private lateinit var rateAdapter: RateAdapter

	private val client = OkHttpClient()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setupRecyclerView()

		lifecycleScope.launchWhenCreated {
			binding.rvRatesList.isVisible = true
			tvNoNet.visibility = View.VISIBLE
			val response = try {
				RetrofitInstance.api.getRates()
			} catch (e: IOException) {
				Log.e("ERROR", e.message.toString())
				return@launchWhenCreated
			}
			if(response.isSuccessful && response.body() != null) {
				tvNoNet.visibility = View.GONE
				rateAdapter.rates = response.body()!!
			}
		}
	}

	private fun setupRecyclerView() = binding.rvRatesList.apply {
		rateAdapter = RateAdapter()
		rvRatesList.adapter = rateAdapter
		layoutManager = LinearLayoutManager(this@MainActivity)
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)
		return super.onCreateOptionsMenu(menu)
	}
}