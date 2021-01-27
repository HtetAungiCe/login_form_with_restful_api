package com.example.plant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plant.data.loginpojo.CurrentUser
import com.example.plant.network.LoginApi
import com.example.plant.repository.LoginRepository

class LoginViewModel(
    loginApi: LoginApi
) : ViewModel() {
    private var repository:LoginRepository? = null
    init {
          repository = LoginRepository(loginApi)
    }
   fun getCurrentUser(email:String,password:String):MutableLiveData<CurrentUser>{
       return repository?.getCurrentUser(email,password)!!
   }

    fun getResult():MutableLiveData<Boolean>{
        return repository!!.getResult()
    }

    fun getToken():String{
        return repository!!.getToken()
    }

}