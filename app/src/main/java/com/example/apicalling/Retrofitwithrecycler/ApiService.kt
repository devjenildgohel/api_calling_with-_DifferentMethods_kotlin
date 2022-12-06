package com.example.apicalling.Retrofitwithrecycler

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("all.json")
    fun fetchCharacters() : Call<MutableList<CharacterModel>>
}