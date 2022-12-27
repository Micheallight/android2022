package com.testapp.phonerecycle

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity(), ProductAdapter.OnProductClickListener, ProductAdapter.OnProductDeleteClickListener {

    val products = mutableListOf<Product>()
    lateinit var productsList: RecyclerView

    val adapter: ProductAdapter = ProductAdapter(products, this, this)

    lateinit var mainViewModel: MainViewModel

    var pos = 0
    val REQUES_CODE = 10
    var pr = Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        productsList = findViewById(R.id.recyclerView)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = ProductAdapter(products, this, this)

        readDataFromDb()

        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, REQUES_CODE)
        }

        if (products.isEmpty()) {
            Toast.makeText(applicationContext, "Empty db", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUES_CODE && resultCode == RESULT_OK) {
            val newProduct = data!!.getParcelableExtra<Product>("product")!!
            products.add(newProduct)
            productsList.adapter?.notifyItemInserted(products.size-1)
        }
    }

    fun readDataFromDb() {
        products.clear()
        products.addAll(mainViewModel.liveDBDao.value!!.getAll())
        adapter.notifyDataSetChanged()
        if (products.isEmpty()) {
            Toast.makeText(applicationContext, "Empty db", Toast.LENGTH_SHORT).show()
        }
    }

    fun changeActivityView() {
        setContentView(R.layout.activity_main)
        productsList = findViewById(R.id.recyclerView)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = ProductAdapter(products, this, this)

        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, REQUES_CODE)
        }
    }

    override fun onProductClick(product: Product, position: Int) {
        pos = position

        val textView = TextView(this)

        with(textView) {
            textView.text = products[position].name
            textView.textSize = 24.0F
            textView.setTextColor(Color.parseColor("#000000"))
            textView.setTypeface(null, Typeface.ITALIC)
            textView.gravity = Gravity.CENTER
        }
        AlertDialog.Builder(this)
            .setCustomTitle(textView)
            .setMessage("Цена: ${products[position].price} руб.\nРейтинг: ${products[position].rating}")
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    override fun onDelete(position: Int) {
        println("list size: ${products.size}")
        val product = products[position]
        mainViewModel.liveDBDao.value!!.deleteByName(product.name)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        println("list size: ${products.size}")
    }


}