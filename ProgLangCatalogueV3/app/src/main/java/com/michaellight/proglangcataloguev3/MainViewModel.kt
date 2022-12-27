package com.michaellight.wishlistv1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val liveDB = MutableLiveData<WishDB>().apply {
        value = Room.databaseBuilder(
            application,
            WishDB::class.java, "product_db"
        ).allowMainThreadQueries()//Делаем в отдельном потоке, в данном случае в главном потоке
            .build()
    }

    val liveDBDao = MutableLiveData<WishDAO>().apply {
        value = liveDB.value!!.productDao()
    }

    val liveWishList = MutableLiveData<List<Wish>>().apply {
        value = listOf()
    }
}