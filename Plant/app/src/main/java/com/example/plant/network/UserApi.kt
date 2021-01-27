package com.example.plant.network

import com.example.plant.data.userpojo.UserResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface UserApi {

    @FormUrlEncoded
    @POST("users")
    fun getUser(@Field("x-access-token") token:String): Single<UserResponse>
    //
//
//    @GET("users")
//    fun getUser(): Single<ArrayList<User>>
    companion object{
        fun create():UserApi{

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.5:3000/api/")
                .build()

            return  retrofit.create(UserApi::class.java)

        }
    }
}