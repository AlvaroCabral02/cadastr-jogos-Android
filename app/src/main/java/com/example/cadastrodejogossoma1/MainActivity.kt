package com.example.cadastrodejogossoma1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicio do banco de dados
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "banco_artesanatos"
        ).fallbackToDestructiveMigration()
            .build()

        setContent {
            val viewModel: ArtesanatoViewModel by viewModels()
            viewModel.setDao(db.artesanatoDao())

            //render da Tela Principal
            TelaEstoqueArtesanato(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaEstoqueArtesanato(viewModel: ArtesanatoViewModel) {
    var nome by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Estoque de Artesanato Comunitário") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //formulario de cadastro e edicao ---
            Text(text = "Cadastrar Novo Item", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do Artesanato (Ex: Vaso)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = tipo,
                onValueChange = { tipo = it },
                label = { Text("Tipo (Ex: EVA ou Tecido)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = preco,
                onValueChange = { preco = it },
                label = { Text("Preço (R$)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (nome.isNotBlank() && tipo.isNotBlank() && preco.isNotBlank()) {
                        val precoDouble = preco.toDoubleOrNull() ?: 0.0
                        viewModel.adicionarItem(Artesanato(nome = nome, tipo = tipo, preco = precoDouble))
                        nome = ""
                        tipo = ""
                        preco = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar no Estoque")
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Itens em Estoque (Comunidade)", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            //Novo sistema de lista
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.listaEstoque) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = item.nome, style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Tipo: ${item.tipo}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Preço: R$ ${String.format("%.2f", item.preco)}", style = MaterialTheme.typography.bodyMedium)
                            }
                            IconButton(onClick = { viewModel.deletarItem(item.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Deletar Item",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}