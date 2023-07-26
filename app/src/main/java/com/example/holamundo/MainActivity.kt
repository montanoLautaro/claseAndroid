package com.example.holamundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.example.holamundo.databinding.ActivityMainBinding
import com.example.holamundo.superhero.SuperHeroActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var email : Boolean = false
    private var password : Boolean = false

    private lateinit var dbManager : DBmanager


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
            binding.etPassword.clearFocus()
            binding.etEmail.clearFocus()
            if(email && password){
                checkCredentials(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            }
        }
    }


    fun checkCredentials(email: String, password : String){
        dbManager = DBmanager(this)
        dbManager.open()
        val userExists = dbManager.checkCredenciales(email, password)
        dbManager.close()

        if(userExists){
            redirectMenu()
        }else{
            Toast.makeText(applicationContext, "Usuario incorrecto", Toast.LENGTH_SHORT).show()
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