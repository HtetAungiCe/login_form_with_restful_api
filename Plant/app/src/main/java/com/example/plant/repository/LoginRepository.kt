package com.example.plant.repository

import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.plant.data.loginpojo.CurrentUser
import com.example.plant.network.LoginApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginRepository(
    private val loginApi: LoginApi
) {
    private lateinit var liveData: MutableLiveData<CurrentUser>
    private lateinit var result:MutableLiveData<Boolean>
    private var token:String?= null

    fun getCurrentUser(email:String,password:String):MutableLiveData<CurrentUser>{

//        if(!::liveData.isInitialized){
            liveData = MutableLiveData()
            result = MutableLiveData()
            val disposable = loginApi.getUser(email,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    if (user.success) {
                        token = user.token
                        liveData.value = user.currentUser
                        result.value = true
                    }else{
                        result.value = false
                    }
                    Log.d("login_token", "token : $user")
                },
                    { error -> result.value = false
                        d("login_token",error.message.toString()) })
//        }

        return liveData
    }

    fun getResult():MutableLiveData<Boolean>{
        return result
}

    fun getToken():String{
        return token!!
    }
}