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
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), WishAdapter.OnProductClickListener, WishAdapter.OnProductDeleteClickListener {

    val wishes = mutableListOf<Wish>()
    lateinit var productsList: RecyclerView

    val adapter: WishAdapter = WishAdapter(wishes, this, this)

    lateinit var mainViewModel: MainViewModel

    var pos = 0
    val REQUES_CODE = 10
    var pr = Wish

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        productsList = findViewById(R.id.listOfItems)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = WishAdapter(wishes, this, this)

        readDataFromDb()

        val btnAdd = findViewById<FloatingActionButton>(R.id.buttonAddNewItem)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, REQUES_CODE)
        }

        if (wishes.isEmpty()) {
            Toast.makeText(applicationContext, "Empty db", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUES_CODE && resultCode == RESULT_OK) {
            val newWish = data!!.getParcelableExtra<Wish>("product")!!
            wishes.add(newWish)
            productsList.adapter?.notifyItemInserted(wishes.size-1)
        }
    }

    fun readDataFromDb() {
        wishes.clear()
        wishes.addAll(mainViewModel.liveDBDao.value!!.getAll())
        adapter.notifyDataSetChanged()
        if (wishes.isEmpty()) {
            Toast.makeText(applicationContext, "Empty db", Toast.LENGTH_SHORT).show()
        }
    }

    fun changeActivityView() {
        setContentView(R.layout.activity_main)
        productsList = findViewById(R.id.listOfItems)
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = WishAdapter(wishes, this, this)

        val btnAdd = findViewById<Button>(R.id.buttonAddNewItem)

        btnAdd.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivityForResult(intent, REQUES_CODE)
        }
    }

    override fun onProductClick(wish: Wish, position: Int) {
        pos = position

        val textView = TextView(this)

        with(textView) {
            textView.text = wishes[position].name
            textView.textSize = 24.0F
            textView.setTextColor(Color.parseColor("#000000"))
            textView.setTypeface(null, Typeface.ITALIC)
            textView.gravity = Gravity.CENTER
        }
    }

    override fun onDelete(position: Int) {
        println("list size: ${wishes.size}")
        val product = wishes[position]
        mainViewModel.liveDBDao.value!!.deleteByDate(product.date)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        println("list size: ${wishes.size}")
    }
}