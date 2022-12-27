package com.testapp.phonerecycle

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room

class ItemActivity : AppCompatActivity() {

    val products = mutableListOf<Product>()
    var photoBmp: Bitmap?=null
    val CAMERA_REQUEST_CODE = 20
    lateinit var photoView: ImageView
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val editName = findViewById<EditText>(R.id.editName)
        val editPrice = findViewById<EditText>(R.id.editPrice)
        val editRate = findViewById<EditText>(R.id.editRate)
        val btnOK = findViewById<Button>(R.id.buttonOK)
        photoView = findViewById(R.id.photo)

        btnOK.setOnClickListener {
            val resultIntent = Intent()

            val newProduct = Product(editName.text.toString(),
                editPrice.text.toString().toInt(),
                R.drawable.tecno_spark_8c,
                editRate.text.toString().toDouble(),
                photoBmp
            )

            mainViewModel.liveDBDao.value!!.addNew(newProduct)

            resultIntent.putExtra("product", newProduct)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        photoView.setOnClickListener() {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
            photoBmp = data?.extras?.get("data") as Bitmap
        photoView.setImageBitmap(photoBmp)
    }
}