package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.databinding.ActivityDetalheProdutoBinding
import com.example.orgs.extensions.carregarImagem
import com.example.orgs.extensions.formataPraMoedaBrasileira
import com.example.orgs.model.Produto

private const val TAG = "DetalhesProduto"
class DetalheProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        carregaProduto()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button_deletar -> {
                Log.i(TAG, "onOptionsItemSelected: remover")
            }
            R.id.button_editar -> {
                Log.i(TAG, "onOptionsItemSelected: editar")
            }
        }
        return super.onOptionsItemSelected(item)
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

