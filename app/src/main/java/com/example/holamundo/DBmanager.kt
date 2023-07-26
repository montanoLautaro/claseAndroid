package com.example.holamundo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DBmanager(context : Context) {

    private val _connection : DBconnection
    private var _baseDatos : SQLiteDatabase? = null

    companion object {
        const val USER_TABLE_NAME = "user"
        const val USER_COLUMN_EMAIL = "email"
        const val USER_COLUMN_PASSWORD = "password"
    }

    init {
        _connection = DBconnection(context)
    }

    fun open() :DBmanager{
        _baseDatos = _connection.writableDatabase
        _connection.onCreate(_baseDatos)
        return this
    }

    fun close(){
        _connection.close()
    }

    fun crearUsuario(user : User): Boolean{
        val contentValues = ContentValues()
        contentValues.put(USER_COLUMN_EMAIL, user.email)
        contentValues.put(USER_COLUMN_PASSWORD, user.password)
        val result = _baseDatos?.insert(USER_TABLE_NAME, null, contentValues)
        println("CREAR USUARIO: $result")
        return if(result==-1L){
            Log.d("insercion", "incorrecta")
            false
        }else{
            Log.d("insercion", "correcta")
            true
        }
    }

    fun getUser(email : String) : User{
        var user : User = User("","")

        val cursor = _baseDatos?.rawQuery("select * from $USER_TABLE_NAME where email='$email'", null)

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    user.email = cursor.getString(0)
                    user.password = cursor.getString(1)
                }while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return user
    }

    fun deleteUser(email: String){
        _baseDatos?.delete(USER_TABLE_NAME, "email='$email'", null)
    }

    fun checkCredenciales(email: String, password : String) : Boolean{
        val cursor = _baseDatos?.rawQuery("select * from $USER_TABLE_NAME where email='$email' AND password='$password'", null)

        val matchFound = cursor?.count!! > 0
        println("CONTADOR: $matchFound y ${cursor.count}")
        cursor.close()
        return matchFound
    }



}