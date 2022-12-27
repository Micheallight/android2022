package com.michaellight.navigationdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel: ViewModel() {
    val person: Person? = null
    val livePerson: MutableLiveData<Person> = MutableLiveData(Person("test", "test", 30))
}