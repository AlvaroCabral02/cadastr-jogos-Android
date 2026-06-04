package com.example.cadastrodejogossoma1

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity () {
    private lateinit var etTitulo: EditText
    private lateinit var etPlataforma: EditText
    private lateinit var etAno: EditText
    private lateinit var btnSalvar: Button

    private var jogoExistente: Jogo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        etTitulo = findViewById(R.id.etTitulo)
        etPlataforma = findViewById(R.id.etPlataforma)
        etAno = findViewById(R.id.etAno)
        btnSalvar = findViewById(R.id.btnSalvar)

        jogoExistente = intent.getSerializableExtra("Jogo Escolhido") as? Jogo

        if (jogoExistente != null) {
            etTitulo.setText(jogoExistente!!.titulo)
            etPlataforma.setText(jogoExistente!!.plataforma)
            etAno.setText(jogoExistente!!.anoLancamento.toString())
            btnSalvar.text = "Atualizar Jogo"
        }

        btnSalvar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val plataforma = etPlataforma.text.toString()
            val anoStr = etAno.text.toString()

            if (titulo.isEmpty() || plataforma.isEmpty() || anoStr.isEmpty()) {
                Toast.makeText(
                    this, "Por favor, preencha os campos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val ano = anoStr.toInt()

            if (jogoExistente != null)
                jogoExistente!!.titulo = titulo
            jogoExistente!!.plataforma = plataforma
            jogoExistente!!.anoLancamento = ano

            JogoRepository.editar(jogoExistente!!)
            Toast.makeText(
                this, "Jogo Atualizado",
                Toast.LENGTH_SHORT
            ).show()

            val novoJogo = Jogo(0, titulo, plataforma, ano)
            JogoRepository.adicionar(novoJogo)
            Toast.makeText(
                this, "Jogo cadastrado com sucesso",
                Toast.LENGTH_SHORT
            ).show()
        }

        finish()

    }
}