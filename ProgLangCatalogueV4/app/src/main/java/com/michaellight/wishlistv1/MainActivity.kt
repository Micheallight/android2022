package com.michaellight.wishlistv1

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), LangAdapter.OnProductClickListener, LangAdapter.OnProductDeleteClickListener {

    val langs = mutableListOf<Lang>()
    lateinit var productsList: RecyclerView

    val adapter: LangAdapter = LangAdapter(langs, this, this)

    lateinit var mainViewModel: MainViewModel

    var pos = 0
    val REQUES_CODE = 10
    var pr = Lang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        productsList = findViewById(R.id.listOfItems)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = LangAdapter(langs, this, this)

        readDataFromDb()

        val btnAdd = findViewById<FloatingActionButton>(R.id.buttonAddNewItem)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, REQUES_CODE)
        }

        if (langs.isEmpty()) {
            Toast.makeText(applicationContext, "Empty db", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUES_CODE && resultCode == RESULT_OK) {
            val newLang = data!!.getParcelableExtra<Lang>("product")!!
            langs.add(newLang)
            productsList.adapter?.notifyItemInserted(langs.size-1)
        }
    }


    fun readDataFromDb() {
        langs.clear()
        langs.addAll(mainViewModel.liveDBDao.value!!.getAll())
        adapter.notifyDataSetChanged()
        if (langs.isEmpty()) {
            Toast.makeText(applicationContext, "Empty db", Toast.LENGTH_SHORT).show()
        }
    }

    fun changeActivityView() {
        setContentView(R.layout.activity_main)
        productsList = findViewById(R.id.listOfItems)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = LangAdapter(langs, this, this)

        val btnAdd = findViewById<Button>(R.id.buttonAddNewItem)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, REQUES_CODE)
        }
    }

    override fun onProductClick(lang: Lang, position: Int) {
        pos = position

        val textView = TextView(this)

        var i = Intent(this, ItemActivity::class.java)
        i.putExtra("product", lang)
        startActivity(i)


//        with(textView) {
//            textView.text = langs[position].name
//            textView.textSize = 24.0F
//            textView.setTextColor(Color.parseColor("#000000"))
//            textView.setTypeface(null, Typeface.ITALIC)
//            textView.gravity = Gravity.CENTER
//        }


    }

    override fun onDelete(position: Int) {
        println("list size: ${langs.size}")
        val lang = langs[position]
        mainViewModel.liveDBDao.value!!.deleteByName(lang.name)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        println("list size: ${langs.size}")
    }
}