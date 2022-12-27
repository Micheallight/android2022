package com.example.retrofitsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
	val apiService = ApiService.create()
	lateinit var tv: TextView
	var posts = listOf<Post>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		tv = findViewById<TextView>(R.id.tv)
		val btnPosts = findViewById<Button>(R.id.btnPosts)
		btnPosts.setOnClickListener() {
			getPosts()
		}
	}

	fun getPosts() {
		val call = apiService.getPosts()
		call.enqueue(object: Callback<List<Post>>{
			override fun onFailure(call: Call<List<Post>>, t: Throwable) {
				Log.d("!!!", t.toString())
			}

			override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
				posts = response.body()!!
				tv.text = posts.toString()
				Log.d("!!!posts",posts.toString())
			}
		})
	}
}

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.TextView
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//class MainActivity : AppCompatActivity() {
//	val apiService = ApiService.create()
//	lateinit var tv:TextView
//	var posts = listOf<Post>()
//	override fun onCreate(savedInstanceState: Bundle?) {
//		super.onCreate(savedInstanceState)
//		setContentView(R.layout.activity_main)
//		tv = findViewById<TextView>(R.id.textView)
//		val btnPosts = findViewById<Button>(R.id.btnPosts)
//		btnPosts.setOnClickListener {
//			getPosts()
//		}
//	}
//	fun getPosts() {
//		val call = apiService.getPosts()
//		call.enqueue(object: Callback<List<Post>> {
//			override fun onFailure(call: Call<List<Post>>, t: Throwable) {
//				Log.d("!!!",t.toString())
//			}
//			override fun onResponse(call: Call<List<Post>>,
//			                        response: Response<List<Post>>) {
//				posts = response.body()!!
//				tv.text = posts.toString()
//				Log.d("!!!posts",posts.toString())
//			}
//		})
//	}
//}

// 1. get comment
// 2. get user
// 3. display users

// make getcomments
// make getusers
// make list view