package com.example.holamundo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBconnection(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table if not exists user(email text not null, password text not null);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXIST" + DBmanager.USER_TABLE_NAME)
        onCreate(db)
    }

    companion object{
        private const val  DATABASE_VERSION = 2
        private const val DATABASE_NAME = "database.db"
    }
}