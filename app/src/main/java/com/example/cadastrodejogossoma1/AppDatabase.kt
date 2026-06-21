package com.example.cadastrodejogossoma1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, "banco_artesanatos.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // cria a tabela apartir do SQL
        db.execSQL(
            "CREATE TABLE tabela_artesanato (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "tipo TEXT, " +
                    "preco REAL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tabela_artesanato")
        onCreate(db)
    }
}