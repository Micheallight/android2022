package com.michaellight.wishlistv1

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_edit_lang.*
import kotlinx.android.synthetic.main.item_wish.*

class ItemActivity : AppCompatActivity() {
    var isForEdit = false

    val langs = mutableListOf<Lang>()
    var photoBmp: Bitmap ?= null
    val CAMERA_REQUEST_CODE = 20
    lateinit var photoView: ImageView
    lateinit var btnAddPhoto: ImageButton
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_lang)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val editName = findViewById<EditText>(R.id.editName)
        val editDate = findViewById<EditText>(R.id.editDate)
        val editText = findViewById<EditText>(R.id.editText)
        val btnOK = findViewById<FloatingActionButton>(R.id.buttonOK)

        photoView = findViewById(R.id.choosedImage)

        btnAddPhoto = findViewById(R.id.buttonTakePhoto)

        btnOK.setOnClickListener {
            val resultIntent = Intent()

            val newLang = Lang(
                editName.text.toString(),
                editDate.text.toString(),
                editText.text.toString(),
                R.drawable.ic_no_photo,
                photoBmp
            )

            if (!isForEdit) {
                mainViewModel.liveDBDao.value!!.addNew(newLang)
            }

            resultIntent.putExtra("product", newLang)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        btnAddPhoto.setOnClickListener() {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }

        getIntents()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            photoBmp = data?.extras?.get("data") as Bitmap
        }
        photoView.setImageBitmap(photoBmp)
    }


    fun getIntents() {
        val i = intent
        if (i != null) {
            if (i.getParcelableExtra<Lang>("product")?.name != null && i.getParcelableExtra<Lang>("product")?.name != "") {
                choosedImage.setImageBitmap(i.getParcelableExtra<Lang>("product")!!.photo)
                editName.setText(i.getParcelableExtra<Lang>("product")?.name)
                editDate.setText(i.getParcelableExtra<Lang>("product")?.date)
                editText.setText(i.getParcelableExtra<Lang>("product")?.text)

                isForEdit = true
            }
//            if (i.getStringExtra(mainViewModel.liveDBDao.name) != null) {
//                itemName.setText(i.getStringExtra(PLCIntentConstants.INTENT_TITLE_KEY))
//                etDate.setText(i.getStringExtra(PLCIntentConstants.INTENT_DATE_KEY))
//                etText.setText(i.getStringExtra(PLCIntentConstants.INTENT_TEXT_KEY))
//
//                id = i.getIntExtra(PLCIntentConstants.INTENT_ID_KEY, 0)
//            }
        }
    }
}