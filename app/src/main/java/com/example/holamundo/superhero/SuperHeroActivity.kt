package com.example.holamundo.superhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.core.view.isVisible
import com.example.holamundo.databinding.ActivitySuperHeroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySuperHeroBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        retrofit = getRetrofit()

        binding.progressbar.isVisible = false

        initUi()

    }

    private fun initUi() {
         binding.svSuperHero.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(nombre: String?): Boolean {
                 buscarPorNombre(nombre)
                 return true
             }

             override fun onQueryTextChange(text: String?): Boolean {
                 println(text)
                 return true
             }

         })
    }

    private fun buscarPorNombre(nombre: String?) {
        binding.progressbar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {

            if (!nombre.isNullOrEmpty()){
                val response : Response<SuperHeroDataResponse> = retrofit.create(ApiSuperHero::class.java).obtenerSuperHeroes(nombre)

                if (response.isSuccessful){
                    val body : SuperHeroDataResponse? = response.body()
                    if (body != null){
                        println("-----------RESPONSE: $response")
                        println("-----------BODY: $body")
                    }
                }

            }


        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}