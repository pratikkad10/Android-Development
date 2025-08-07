package com.example.authenticationusingsql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context) :  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object{
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "EMAIL"
        private const val COLUMN_PASSWORD = "PASSWORD"
    }



    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery= ("CREATE TABLE $TABLE_NAME ( "
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "$COLUMN_EMAIL TEXT UNIQUE , "
                + "$COLUMN_PASSWORD TEXT)")

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val dropTableQuery = ("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL(dropTableQuery)
        onCreate(db)  //re-creates the table after deletion
    }

    fun insertUser(email : String, password: String): Long{
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }

        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readUser(email: String, password: String): Boolean{
        val db = readableDatabase
        val selection = "$COLUMN_EMAIL =? AND $COLUMN_PASSWORD =?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val userExist = cursor.count > 0
        cursor.close()
        return userExist
    }

}