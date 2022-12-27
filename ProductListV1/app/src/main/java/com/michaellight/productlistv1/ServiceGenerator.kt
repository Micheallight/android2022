package com.michaellight.productlistv1

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
//	const val BASE_URL = "https://dummyjson.com/products/"
	const val BASE_URL = "https://raw.githubusercontent.com/Micheallight/api/main/api.json/"

	private val client = OkHttpClient.Builder().build()

	private val retrofit = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.client(client)
		.client(client)
		.build()

	fun <T> BuildService(service: Class<T>): T {
		return retrofit.create(service)
	}
}