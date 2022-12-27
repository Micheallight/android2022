package com.testapp.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.bank_entity.*

class ExchangeActivity : AppCompatActivity() {
    val REQUEST_CODE = 10
    var currentExchange = Exchange("","","","","","","","","","","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)

        //val filialID = findViewById<TextView>(R.id.filialId)
        val city = findViewById<TextView>(R.id.city)
        val workTime = findViewById<TextView>(R.id.workTime)
        var street = findViewById<TextView>(R.id.street)
        val usdIn = findViewById<TextView>(R.id.usdIn)
        val usdOut = findViewById<TextView>(R.id.usdOut)
        var eurIn = findViewById<TextView>(R.id.euroIn)
        var eurOut = findViewById<TextView>(R.id.euroOut)
        var rubIn = findViewById<TextView>(R.id.rubIn)
        var rubOut = findViewById<TextView>(R.id.rubOut)

        currentExchange = intent.getParcelableExtra("product")!!
        println(currentExchange.filial_id)
        filialId.text = currentExchange.filial_id
        println(currentExchange.name)
        city.text = currentExchange.name
        workTime.text = ""
        println(currentExchange.info_worktime)
        for(char in currentExchange.info_worktime){
            if (!char.equals('|')){
                workTime.text = workTime.text.toString() + char.toString()
            }else{
                workTime.text = workTime.text.toString() + "\n"
            }
        }
        println(currentExchange.street_type + currentExchange.street + " " + currentExchange.home_number)
        street.text = currentExchange.street_type + currentExchange.street + " " + currentExchange.home_number
        println(currentExchange.USD_in)
        usdIn.text = currentExchange.USD_in
        print(currentExchange.USD_out)
        usdOut.text = currentExchange.USD_out
        println(currentExchange.EUR_in)
        eurIn.text = currentExchange.EUR_in
        println(currentExchange.EUR_out)
        eurOut.text = currentExchange.EUR_out
        println(currentExchange.RUB_in)
        rubIn.text = currentExchange.RUB_in
        println(currentExchange.RUB_out)
        rubOut.text = currentExchange.RUB_out

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            currentExchange = data!!.getParcelableExtra<Exchange>("product")!!
        }
    }
}