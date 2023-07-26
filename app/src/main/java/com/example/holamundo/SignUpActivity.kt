package com.example.holamundo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.holamundo.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var dbManager : DBmanager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)



        binding.btnCrear.setOnClickListener {
            val user = User(binding.etEmail.text.toString(), binding.etPassword.text.toString())

            dbManager = DBmanager(this)
            dbManager.open()
            dbManager.crearUsuario(user)
            dbManager.close()

            Log.d("NUEVO USUARIO", "USUARIO: ${binding.etEmail.text.toString()}, CONTRASEÑA: ${binding.etPassword.text.toString()}")
        }
    }




}