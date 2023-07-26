package com.example.holamundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.holamundo.databinding.ActivityMainBinding
import com.example.holamundo.superhero.SuperHeroActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var email : Boolean = false
    private var password : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        validateEmailFocus()
        validatePasswordFocus()
        initListeners()



    }

    private fun initListeners() {
        binding.etEmail.setOnClickListener{
            binding.etEmail.backgroundTintList = getColorStateList(R.color.black)
        }

        logIn()
        signUp()
    }

    private fun signUp() {
        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun logIn(){
        binding.btnIngresar.setOnClickListener {

            redirectMenu()

            /*
            if(email && password){
                redirectMenu()
            }

             */
        }
    }

    private fun redirectMenu() {
        val intent = Intent(this, SuperHeroActivity::class.java)
        startActivity(intent)
    }

    fun validateEmailFocus(){
        binding.etEmail.setOnFocusChangeListener { _, focus ->
            if(!focus){
                validateEmail()
            }
        }
    }

    fun validatePasswordFocus(){
        binding.etPassword.setOnFocusChangeListener { _, focus ->

            if(!focus){
                validatePassword()
            }
        }
    }


    fun validateEmail(){
        email = android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()

    }

    fun validatePassword(){
        password = binding.etPassword.text.length > 4
    }
}