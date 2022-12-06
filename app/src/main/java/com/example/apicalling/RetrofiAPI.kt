package com.example.apicalling

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofiAPI {

    @GET("id/31.json")
    fun getHero() : Call<DataModel?>?

    companion object{
        fun create() : RetrofiAPI{

            val interceptor : HttpLoggingInterceptor? = HttpLoggingInterceptor()
            interceptor!!.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofiAPI = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://akabab.github.io/superhero-api/api/")
                .client(client)
                .build()


            return retrofiAPI.create(RetrofiAPI::class.java)
        }
    }
}