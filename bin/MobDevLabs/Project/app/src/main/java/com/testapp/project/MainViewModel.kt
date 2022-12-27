package com.testapp.project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val liveDB = MutableLiveData<ExchangeSavedDB>().apply {
        value = Room.databaseBuilder(
            application,
            ExchangeSavedDB::class.java, "exchange_db"
        ).allowMainThreadQueries()
            .build()
    }

    val liveDBDao = MutableLiveData<ExchangeSavedDao>().apply {
        value = liveDB.value!!.exchangeSavedDao()
    }

}