package com.example.cadastrodejogossoma1

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tabela_artesanato")
data class Artesanato(
    @PrimaryKey(autoGenerate = true) //ID unico com geração pelo banco
    var id: Int = 0,
    var nome: String,
    var tipo: String, // EVA ou Tecido
    var preco: Double
) : Serializable