package com.example.holamundo.superhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val respuesta: String,
    @SerializedName("results") val superHeroes: List<SuperHeroItem>
)

data class SuperHeroItem(
    @SerializedName("id") val id: String,
    @SerializedName("name") val nombre: String,
    @SerializedName("image") val image: SuperHeroImage
)

data class SuperHeroImage(
    @SerializedName("url") val url: String
)