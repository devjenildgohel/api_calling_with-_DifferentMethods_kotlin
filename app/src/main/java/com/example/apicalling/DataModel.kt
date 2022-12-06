package com.example.apicalling

data class DataModel(
    val id : String,
    val name : String,
    val slug : String,
    val powerstats : Map<String,Int>,
    val  images : Map<String,String>
)