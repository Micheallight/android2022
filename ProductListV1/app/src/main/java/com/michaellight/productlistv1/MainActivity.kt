package com.michaellight.productlistv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

		val serviceGenerator = ServiceGenerator.BuildService(ApiService::class.java)
		Log.d("TAG", serviceGenerator.toString())
		val call = serviceGenerator.getPosts()

//		val button = findViewById<Button>(R.id.btnClick)
//		button.setOnClickListener {
//		}
			call.enqueue(object : Callback<MutableList<PostModel>> {
				override fun onResponse(call: Call<MutableList<PostModel>>, response: Response<MutableList<PostModel>>) {
					if (response.isSuccessful) {
						Log.e("success1111", response.body().toString())
						recyclerView.apply {
							layoutManager = LinearLayoutManager(this@MainActivity)
							adapter = PostAdapter(response.body()!!)
						}
					}
				}

				override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
					t.printStackTrace()
					Log.e("error1111", t.message.toString())
				}
			})
	}
}