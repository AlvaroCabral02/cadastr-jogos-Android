package com.example.cadastrodejogossoma1

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtesanatoViewModel : ViewModel() {

    private lateinit var dao: ArtesanatoDao

    // Uma lista observável pelo Jetpack Compose. Quando mudamos essa lista, a tela atualiza na hora!
    val listaEstoque = mutableStateListOf<Artesanato>()

    fun setDao(artesanatoDao: ArtesanatoDao) {
        this.dao = artesanatoDao
        carregarItens()
    }

    // O Room exige que buscas e gravações rodem fora da linha principal do app (em segundo plano)
    // por isso usamos o "viewModelScope.launch(Dispatchers.IO)"
    fun carregarItens() {
        viewModelScope.launch(Dispatchers.IO) {
            val itens = dao.listarTodos()
            viewModelScope.launch(Dispatchers.Main) {
                listaEstoque.clear()
                listaEstoque.addAll(itens)
            }
        }
    }

    fun adicionarItem(artesanato: Artesanato) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.inserir(artesanato)
            carregarItens() // Atualiza a lista na tela
        }
    }

    fun editarItem(artesanato: Artesanato) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.editar(artesanato)
            carregarItens()
        }
    }

    fun deletarItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deletarPorId(id)
            carregarItens()
        }
    }
}