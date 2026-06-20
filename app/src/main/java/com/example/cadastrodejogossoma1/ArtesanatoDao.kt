package com.example.cadastrodejogossoma1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ArtesanatoDao {

    @Query("SELECT * FROM tabela_artesanato")
    fun listarTodos(): List<Artesanato>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserir(artesanato: Artesanato)

    @Update
    fun editar(artesanato: Artesanato)

    @Query("DELETE FROM tabela_artesanato WHERE id = :id")
    fun deletarPorId(id: Int)
}