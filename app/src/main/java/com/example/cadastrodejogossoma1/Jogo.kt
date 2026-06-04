package com.example.cadastrodejogossoma1
import java.io.Serializable

data class Jogo (
    var id: Int,
    var titulo: String,
    var plataforma: String,
    var anoLancamento: Int
) : Serializable