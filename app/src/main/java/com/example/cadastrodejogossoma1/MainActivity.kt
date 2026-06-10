package com.example.cadastrodejogossoma1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastrodejogossoma1.FormularioActivity
import com.example.cadastrodejogossoma1.JogoAdapter
import com.example.cadastrodejogossoma1.JogoRepository
import com.example.cadastrodejogossoma1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rvJogos: RecyclerView
    private lateinit var fabAdicionar: FloatingActionButton
    private lateinit var jogoAdapter: JogoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvJogos = findViewById(R.id.rvJogos)
        fabAdicionar = findViewById(R.id.fabAdicionar)

        rvJogos.layoutManager = LinearLayoutManager(this)

        jogoAdapter = JogoAdapter(
            lista = JogoRepository.listarTodos(),
            onEditClick = { jogo ->
                val intent = Intent(this, FormularioActivity::class.java)
                intent.putExtra("JOGO_SELECIONADO", jogo)
                startActivity(intent)
            },
            onDeleteClick = { jogo ->
                // 1. Remove do "banco de dados" interno
                JogoRepository.remover(jogo.id)

                // 2. Força o Adapter a reler a lista atualizada para sumir com o item na hora
                jogoAdapter.atualizarLista(JogoRepository.listarTodos())
            }
        )

        rvJogos.adapter = jogoAdapter

        fabAdicionar.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (::jogoAdapter.isInitialized) {
            jogoAdapter.atualizarLista(JogoRepository.listarTodos())
        }
    }
}