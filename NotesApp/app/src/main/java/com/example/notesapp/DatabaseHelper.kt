package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class DatabaseHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){




    companion object{
        private const val DATABASE_NAME = "NotesApp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"

        private const val COLUMN_CREATEDAT = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER UNIQUE PRIMARY KEY, "
                + "$COLUMN_TITLE TEXT, "
                + "$COLUMN_CONTENT TEXT, "
                + "$COLUMN_CREATEDAT TEXT)")
        db?.execSQL(createQuery)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertNote(notes: Notes){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, notes.title)
            put(COLUMN_CONTENT, notes.content)
            put(COLUMN_CREATEDAT, notes.createdDate)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getAllNotes() : List<Notes>{
        val notesList = mutableListOf<Notes>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            val currentDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATEDAT))

            val notes = Notes(id, title, content, currentDate)
            notesList.add(notes)
        }
        cursor.close()
        db.close()
        return notesList
    }

    fun updateNote(note: Notes){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)
            put(COLUMN_CREATEDAT, note.createdDate)
        }

        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(note.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getNotesById(noteId : Int): Notes{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $noteId"
        val cursor= db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
        val createdAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATEDAT))

        cursor.close()
        db.close()
        return Notes(id, title, content, createdAt)
    }

    fun deleteNote(noteId: Int?){
       val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(noteId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()

    }
}
