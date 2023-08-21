package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.databinding.ActivityDetalheProdutoBinding
import com.example.orgs.extensions.carregarImagem
import com.example.orgs.extensions.formataPraMoedaBrasileira
import com.example.orgs.model.Produto

class DetalheProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        carregaProduto()

    }

    fun carregaProduto() {

        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheValores(produtoCarregado)
        } ?: finish()
    }

    private fun preencheValores(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesTvTitulo.text = produtoCarregado.nome
            activityDetalhesTvValor.text = produtoCarregado.valor.formataPraMoedaBrasileira()
            activityDetalhesTvDescricao.text = produtoCarregado.descricao
            activityDetalhesImagem.carregarImagem(produtoCarregado.imagem)
        }
    }

}

