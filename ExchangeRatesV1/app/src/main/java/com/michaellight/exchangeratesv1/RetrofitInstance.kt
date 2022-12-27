package com.michaellight.exchangeratesv1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
	val BASE_URL = "https://www.nbrb.by/api/exrates/"

	val api: RateAPI by lazy {
		Retrofit
			.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(RateAPI::class.java)
	}
}