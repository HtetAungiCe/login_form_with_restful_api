package com.example.plant.network

import com.example.plant.data.loginpojo.LoginResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @FormUrlEncoded
    @POST("login")
    fun getUser(@Field("email") email:String,
                @Field("password") password:String): Single<LoginResponse>

    companion object{
        fun create():LoginApi{

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.5:3000/api/")
                .build()

            return  retrofit.create(LoginApi::class.java)

        }
    }
}