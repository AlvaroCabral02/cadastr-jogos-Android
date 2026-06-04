package com.example.cadastrodejogossoma1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JogoAdapter (
    private var lista: List<Jogo>,
    private val onEditClick: (Jogo) -> Unit,
    private val onDeleteClick: (Jogo) -> Unit
) : RecyclerView.Adapter<JogoAdapter.JogoViewHolder>() {//colchetes do Recycler

    class JogoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtPlataforma: TextView = itemView.findViewById(R.id.txtPlataforma)
        val btnDeletar: ImageButton = itemView.findViewById(R.id.btnDeletar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jogo,
            parent, false)
        return JogoViewHolder(view)
    }

    override fun onBindViewHolder(holder: JogoViewHolder, position: Int) {
        val jogo = lista[position]
        holder.txtTitulo.text = jogo.titulo
        holder.txtPlataforma.text = "${jogo.plataforma} (${jogo.anoLancamento})"
        holder.itemView.setOnClickListener { onEditClick(jogo) }//aviso de ediçãom do jogo
        holder.btnDeletar.setOnClickListener { onDeleteClick(jogo)}//avisar que vai deletar o jogo
    }

    override fun getItemCount(): Int = lista.size

    fun atualizarlista (novaLista: List<Jogo>) {
        this.lista = novaLista
        notifyDataSetChanged()
    }

    fun atualizarLista(listarTodos: kotlin.collections.List<com.example.cadastrodejogossoma1.Jogo>) {}

}