package com.michaellight.bankapiv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
	private val apiService = ApiService.create()
	private var exchangeList = arrayListOf<Exchange>()
	private val adapter: ExchangeAdapter = ExchangeAdapter(exchangeList, this)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}