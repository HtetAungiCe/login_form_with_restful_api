package com.example.plant.repository

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.plant.data.loginpojo.CurrentUser
import com.example.plant.data.userpojo.User
import com.example.plant.network.UserApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepository(
    private val userApi: UserApi
) {
    private lateinit var liveData: MutableLiveData<ArrayList<User>>
    private lateinit var result: MutableLiveData<Boolean>

    fun getUser(token: String): MutableLiveData<ArrayList<User>> {
        if (!::liveData.isInitialized) {
            liveData = MutableLiveData()
            result = MutableLiveData()

            val disposable = userApi.getUser(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    Log.d("repo_main", ""+user.users)
                    liveData.value = user.users
                    result.value = true
                },
                    { error ->
                        result.value = false
                        d("repo_main", error.message.toString())
                    }
                )

        }
        return liveData
        Log.d("repo_main", ""+liveData)
    }

    fun getResult():MutableLiveData<Boolean>{
        return result
    }

}