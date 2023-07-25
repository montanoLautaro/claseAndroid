package com.example.holamundo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

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
        return result != -1L
    }

}