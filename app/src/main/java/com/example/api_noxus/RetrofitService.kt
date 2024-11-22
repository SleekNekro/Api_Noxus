package com.example.api_noxus

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("champions")
    suspend fun listChamps(
        @Query("apikey") apikey:String,
        @Query("select") select:String ="*"
    ): ArrayList<Champ>
}
object RetrofitServiceFactory{
    fun makeRetrofitService():RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://qxuuogbicruvbatgqjou.supabase.co/rest/v1/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}