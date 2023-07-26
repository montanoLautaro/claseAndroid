package com.example.holamundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.holamundo.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var dbManager : DBmanager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)



        binding.btnCrear.setOnClickListener {
            crearUsuario()
        }

        binding.btnCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun crearUsuario(){
        dbManager = DBmanager(this)
        dbManager.open()

        val existe = dbManager.checkCredenciales(binding.etEmail.text.toString(), binding.etPassword.text.toString())

        if(!existe){
            val user = User(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            dbManager.crearUsuario(user)
            Toast.makeText(applicationContext, "usuario creado", Toast.LENGTH_SHORT).show()
            Log.d("NUEVO USUARIO", "USUARIO: ${binding.etEmail.text.toString()}, CONTRASEÑA: ${binding.etPassword.text.toString()}")
        }else{
            Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
        }

        dbManager.close()
    }




}