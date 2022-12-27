package com.example.exchangerates.json

import com.example.exchangerates.Exchange
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

val BASE_URL = "https://belarusbank.by/api/"
interface ApiService {
    @GET("kursExchange")
    fun getPosts(): Call<List<Exchange>>
    companion object Factory {
        fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                //.readTimeout(20,TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}