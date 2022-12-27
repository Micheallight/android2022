package com.example.exchangerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exchangerates.db.ExchangeDao
import kotlinx.android.synthetic.main.activity_exchange_info.*

class ExchangeInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_info)

        val exchangeDto = intent.extras?.getSerializable("exchangeDto")!! as ExchangeDto

        tvAddress.text =
            "${exchangeDto.name_type} ${exchangeDto.name}, ${exchangeDto.street_type} ${exchangeDto.street}, ${exchangeDto.home_number}"
    }
}