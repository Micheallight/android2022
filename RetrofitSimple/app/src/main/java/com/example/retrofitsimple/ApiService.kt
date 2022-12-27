package com.example.retrofitsimple

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface ApiService {
	@GET("posts")
	fun getPosts(): Call<List<Post>>

	companion object Factory {
		fun create(): ApiService {
			val okHttpClient = OkHttpClient.Builder().build()
			val retrofit: Retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(okHttpClient)
				.build()
			return retrofit.create(ApiService::class.java)
		}
	}
}