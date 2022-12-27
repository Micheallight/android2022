package com.michaellight.wishlistv1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val liveDB = MutableLiveData<LangDB>().apply {
        value = Room.databaseBuilder(
            application,
            LangDB::class.java, "lang_bd"
        ).allowMainThreadQueries()
            .build()
    }

    val liveDBDao = MutableLiveData<LangDAO>().apply {
        value = liveDB.value!!.productDao()
    }

    val liveWishList = MutableLiveData<List<Lang>>().apply {
        value = listOf()
    }
}