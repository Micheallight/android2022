package com.example.myexchange

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExchangeActivity : AppCompatActivity() {
    val REQUEST_CODE = 200
    var currentExchange = Rate(0, "", 0.0, "", "", 0, 0, "", 0.0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.rate_layout)

        var date = findViewById<TextView>(R.id.date)
        var sellIso = findViewById<TextView>(R.id.sellIso)
        var sellRate = findViewById<TextView>(R.id.sellRate)
        var buyRate = findViewById<TextView>(R.id.buyRate)
        var buyIso = findViewById<TextView>(R.id.buyIso)
        var quantity = findViewById<TextView>(R.id.quantity_value)
        var sellCode = findViewById<TextView>(R.id.sellCode_value)
        var buyCode = findViewById<TextView>(R.id.buyCode_value)

        currentExchange = intent.getParcelableExtra("product")!!

        date.text = currentExchange.date
        sellIso.text = currentExchange.sellIso
        sellRate.text = currentExchange.sellRate.toString()
        buyRate.text = currentExchange.buyRate.toString()
        buyIso.text = currentExchange.buyIso
        quantity.text = currentExchange.quantity.toString()
        sellCode.text = currentExchange.sellCode.toString()
        buyCode.text = currentExchange.buyCode.toString()
//
//        var cur_id = findViewById<TextView>(R.id.cur_id)
//        var date = findViewById<TextView>(R.id.date)
//        var cur_abbreviation = findViewById<TextView>(R.id.cur_abr_tv)
//        var cur_scale = findViewById<TextView>(R.id.cur_scale_tv)
//        var cur_name = findViewById<TextView>(R.id.cur_name_tv)
//        var cur_officialRate = findViewById<TextView>(R.id.cur_offRate_tv)


//        currentExchange = intent.getParcelableExtra("product")!!
//        cur_id.text = currentExchange.Cur_ID
//        date.text = currentExchange.Date
//        cur_abbreviation.text = currentExchange.Cur_Abbreviation
//        cur_scale.text = currentExchange.Cur_Scale
//        cur_name.text = currentExchange.Cur_Name
//        cur_officialRate.text = currentExchange.Cur_OfficialRate
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            currentExchange = data!!.getParcelableExtra<Rate>("product")!!
        }
    }
}