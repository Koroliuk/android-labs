package com.koroliuk.lab3.storage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTITIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {

        const val DATABASE_NAME = "Lab3.db"
        const val DATABASE_VERSION = 1

        const val SQL_CREATE_ENTITIES = "CREATE TABLE ${DbContract.NoteEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${DbContract.NoteEntry.COLUMN_NAME_TEXT} TEXT," +
                "${DbContract.NoteEntry.COLUMN_NAME_FONT_NAME} TEXT" +
                " )"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DbContract.NoteEntry.TABLE_NAME}"

    }

}