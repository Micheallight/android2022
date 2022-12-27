package com.example.myexchange

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates
const val BASE_URL = "https://developerhub.alfabank.by:8273/partner/1.0.1/"

interface Api {

    @GET("public/rates")
    fun getExchange(): Call<Exchange>


    companion object Factory {
        fun create(): Api {
            val okHttpClient = OkHttpClient.Builder()
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(Api::class.java)
        }
    }

}