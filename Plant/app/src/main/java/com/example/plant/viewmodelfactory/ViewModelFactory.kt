package com.example.plant.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plant.network.LoginApi
import com.example.plant.viewmodels.LoginViewModel
import com.example.plant.viewmodels.MainViewModel

class ViewModelFactory (
    private val loginApi: LoginApi
        ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}