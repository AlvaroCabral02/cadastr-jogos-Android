package com.example.cadastrodejogossoma1

import android.content.ContentValues
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ArtesanatoViewModel : ViewModel() {

    private lateinit var dbHelper: AppDatabase
    val listaEstoque = mutableStateListOf<Artesanato>()

    fun inicializarBanco(context: Context) {
        dbHelper = AppDatabase(context)
        carregarItens()
    }

    fun carregarItens() {
        listaEstoque.clear()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tabela_artesanato", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"))
                val preco = cursor.getDouble(cursor.getColumnIndexOrThrow("preco"))

                listaEstoque.add(Artesanato(id, nome, tipo, preco))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
    }

    fun adicionarItem(artesanato: Artesanato) {
        val db = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("nome", artesanato.nome)
            put("tipo", artesanato.tipo)
            put("preco", artesanato.preco)
        }
        db.insert("tabela_artesanato", null, valores)
        db.close()
        carregarItens() // Atualiza a tela na hora
    }

    fun deletarItem(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("tabela_artesanato", "id = ?", arrayOf(id.toString()))
        db.close()
        carregarItens() // Atualiza a tela na hora
    }
}