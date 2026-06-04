package com.example.cadastrodejogossoma1
object JogoRepository {
    private val listaJogos = mutableListOf<Jogo>()
    private var idAtual = 1

    init {//Para n ter uma tela vazia no comeco
        adicionar(Jogo(idAtual, "The Witcher 3", "Playstation 5", 2015))
        adicionar(Jogo(idAtual, "The Elder Scrolls V: Skyrim", "Playstation 4", 2011))
    }

    fun adicionar(jogo: Jogo){
        jogo.id - idAtual
        listaJogos.add(jogo)
        idAtual++
    }

    fun listarTodos(): List<Jogo> {
        return listaJogos
    }

    fun editar(jogoAtualizado: Jogo) {
        val index = listaJogos.indexOfFirst { it.id == jogoAtualizado.id}
        if (index != -1){
            listaJogos[index] = jogoAtualizado
        }
    }

    fun remover (id: Int) {
        listaJogos.removeAll { it.id == id}
    }
}//colchetes final