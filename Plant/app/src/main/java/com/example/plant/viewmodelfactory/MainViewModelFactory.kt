package com.example.plant.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plant.network.UserApi
import com.example.plant.viewmodels.MainViewModel

class MainViewModelFactory (
   private val userApi: UserApi
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(userApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}