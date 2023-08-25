package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.extensions.carregarImagem
import com.example.orgs.extensions.formataPraMoedaBrasileira
import com.example.orgs.model.Produto

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    var clickNoItemListener: (produto:Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {


    private val produtos = produtos.toMutableList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ProdutoItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false,
        )

        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Produto

        init {
            itemView.setOnClickListener{
                if (::produto.isInitialized){
                    clickNoItemListener(produto)
                }
            }
        }

        private val title = binding.titulo
        private val description = binding.descricao
        private val value = binding.valor

        private val imagem = binding.imagem

        fun vincular(produto: Produto) {
            this.produto = produto

            title.text = produto.nome

            description.text = produto.descricao

            value.text = produto.valor.formataPraMoedaBrasileira()

            imagem.carregarImagem(produto.imagem)

        }

    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincular(produto)
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()

    }

}
