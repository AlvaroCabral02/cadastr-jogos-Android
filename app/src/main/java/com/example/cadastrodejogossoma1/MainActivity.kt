package com.example.cadastrodejogossoma1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity () {
    private lateinit var rvJogos: RecyclerView
    private lateinit var fabAdicionar: FloatingActionButton
    private lateinit var jogoAdapter: JogoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {//criacao da tela inicial
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvJogos = findViewById(R.id.rvJogos)
        fabAdicionar = findViewById(R.id.fabAdicionar)
        rvJogos.layoutManager= LinearLayoutManager(this)
        //JogoAdapter com sua lista e acões
        jogoAdapter = JogoAdapter(
            lista = JogoRepository.listarTodos(),
            onEditClick = { jogo->
                Toast.makeText(this, "Editar: ${jogo.titulo}",
                    Toast.LENGTH_SHORT).show()
            },
            onDeleteClick = { jogo ->
                JogoRepository.remover(jogo.id)
                jogoAdapter.atualizarlista(JogoRepository.listarTodos())
                Toast.makeText(this, "${jogo.titulo} removido",
                    Toast.LENGTH_SHORT).show()
            }
        )//parenteses do JogoAdapter

        rvJogos.adapter = jogoAdapter

        fabAdicionar.setOnClickListener {//Cadastro
            Toast.makeText(this, "Abrir formulario de cadastro",
                Toast.LENGTH_SHORT).show()
        }

    } //colchetes final do onCreate

    override fun onResume() {//metodo que atualiza a tela
        super.onResume()
        if (::jogoAdapter.isInitialized) {
            jogoAdapter.atualizarlista(JogoRepository.listarTodos())
        }
    }//fim do onResume


}//Colchetes do MainActivity