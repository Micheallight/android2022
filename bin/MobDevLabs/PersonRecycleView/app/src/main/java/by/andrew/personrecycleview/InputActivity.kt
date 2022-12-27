package by.andrew.personrecycleview

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class InputActivity : AppCompatActivity() {

    var photoBmp: Bitmap?=null

    val CAMERA_REQUEST_CODE = 20

    lateinit var photoView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        val editName = findViewById<EditText>(R.id.editName)
        val editSecondName = findViewById<EditText>(R.id.editSecondName)
        val editAge = findViewById<EditText>(R.id.editAge)
        val btnOK = findViewById<Button>(R.id.addButton)
        photoView = findViewById(R.id.photo)

        btnOK.setOnClickListener(){
            val resultIntent = Intent()

            val newPerson = Person(editName.text.toString(),
                editSecondName.text.toString(),
                editAge.text.toString().toInt(),
                R.drawable.p6,
                photoBmp
                )

            /*resultIntent.putExtra("name", editName.text.toString())
            resultIntent.putExtra("secondname", editSecondName.text.toString())
            resultIntent.putExtra("age", editAge.text.toString().toInt())
*/
            resultIntent.putExtra("person", newPerson)
            setResult(RESULT_OK, resultIntent)
            finish()
        }


        photoView.setOnClickListener(){
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
            photoBmp = data?.extras?.get("data") as Bitmap
            photoView.setImageBitmap(photoBmp)
    }

}