package com.example.exchangerates.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exchangerates.R
import com.example.exchangerates.data.Exchange
import kotlinx.android.synthetic.main.activity_exchange_info.*

class ExchangeInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_info)

        val exchange = intent.extras?.getSerializable("exchange")!! as Exchange

        tvInfoUsdIn.text = exchange.USD_in
        tvInfoUsdOut.text = exchange.USD_out
        tvInfoAddress.text =
            "${exchange.name_type} ${exchange.name}, ${exchange.street_type} ${exchange.street}, ${exchange.home_number}"
    }
}