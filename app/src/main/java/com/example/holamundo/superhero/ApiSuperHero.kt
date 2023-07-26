package com.example.holamundo.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiSuperHero {

    @GET("10233054305565682/search/{name}")
    suspend fun obtenerSuperHeroes(@Path("name") nombre: String): Response<SuperHeroDataResponse>


}