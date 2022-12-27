package com.testapp.phonerecycle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val liveDB = MutableLiveData<ProductDB>().apply {
        value = Room.databaseBuilder(
            application,
            ProductDB::class.java, "product_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()
    }

    val liveDBDao = MutableLiveData<ProductDao>().apply {
        value = liveDB.value!!.productDao()
    }

    val liveProductList = MutableLiveData<List<Product>>().apply {
        value = listOf()
    }

}