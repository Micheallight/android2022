package com.michaellight.wishlistv1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ItemActivity : AppCompatActivity() {

    val wishes = mutableListOf<Wish>()
    var photoBmp: Bitmap ?= null
    val CAMERA_REQUEST_CODE = 20
    lateinit var photoView: ImageView
    lateinit var btnAddPhoto: ImageButton
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish_customization)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val editName = findViewById<EditText>(R.id.editName)
        val editPrice = findViewById<EditText>(R.id.editPrice)
        val btnOK = findViewById<FloatingActionButton>(R.id.buttonOK)

        photoView = findViewById(R.id.choosedImage)

        btnAddPhoto = findViewById(R.id.buttonTakePhoto)

        btnOK.setOnClickListener {
            val resultIntent = Intent()

            val newWish = Wish(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                editName.text.toString(),
                editPrice.text.toString(),
                R.drawable.ic_no_photo,
                photoBmp
            )

            mainViewModel.liveDBDao.value!!.addNew(newWish)

            resultIntent.putExtra("product", newWish)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        btnAddPhoto.setOnClickListener() {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            photoBmp = data?.extras?.get("data") as Bitmap
        }
        photoView.setImageBitmap(photoBmp)
    }
}