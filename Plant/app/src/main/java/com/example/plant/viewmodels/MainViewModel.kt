package com.example.plant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plant.data.userpojo.User
import com.example.plant.network.UserApi
import com.example.plant.repository.MainRepository

class MainViewModel(
    private val userApi: UserApi
): ViewModel() {
    private var repository:MainRepository?= null
    init {
        repository = MainRepository(userApi)
    }

    fun getUser(token:String):MutableLiveData<ArrayList<User>>{
        return repository!!.getUser(token)
    }

    fun getResult():MutableLiveData<Boolean>{
        return repository!!.getResult()
    }

}