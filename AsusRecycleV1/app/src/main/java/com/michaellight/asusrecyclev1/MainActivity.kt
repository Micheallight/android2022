package com.michaellight.asusrecyclev1

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

	val products = mutableListOf<Product>()
	lateinit var productsList: RecyclerView

	var pos = 0
	val REQUES_CODE = 10
	var pr = Product

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		products.add(Product("ASUS Zenbook Pro", 1100, R.drawable.zenbook_pro, 4.8, null))
		products.add(Product("ASUS Zenbook Duo", 1400, R.drawable.zenbook_duo, 4.6, null))
		products.add(Product("ASUS Vivobook", 1500, R.drawable.vivobook, 4.7, null))

		productsList = findViewById(R.id.recyclerView)
		productsList.layoutManager = LinearLayoutManager(this)
		productsList.adapter = ProductAdapter(products, this)

		val btnAdd = findViewById<TextView>(R.id.btnAdd)

		btnAdd.setOnClickListener {
			val intent = Intent(this, ItemActivity::class.java)
			startActivityForResult(intent, REQUES_CODE)
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

	fun itemClick(position: Int) {
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


	fun changeActivityView() {
		setContentView(R.layout.activity_main)
		productsList = findViewById(R.id.recyclerView)
		productsList.layoutManager = LinearLayoutManager(this)
		productsList.adapter = ProductAdapter(products, this)

		val btnAdd = findViewById<TextView>(R.id.btnAdd)

		btnAdd.setOnClickListener {
			val intent = Intent(this, ItemActivity::class.java)
			startActivityForResult(intent, REQUES_CODE)
		}
	}
}