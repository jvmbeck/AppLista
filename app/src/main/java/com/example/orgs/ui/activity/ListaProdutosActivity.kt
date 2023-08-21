package com.example.orgs.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDAO
import com.example.orgs.databinding.ActivityListaProdutoBinding
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity(){

    private val binding by lazy {
        ActivityListaProdutoBinding.inflate(layoutInflater)

    }

    private val dao = ProdutosDAO()
    private val adapter = ListaProdutosAdapter(
        this,
        produtos = dao.buscaTodos()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configRecyclerView()
        configFAB()

    }

    override fun onResume() {
        super.onResume()

        adapter.atualiza(dao.buscaTodos())
        configFAB()
    }

    private fun configFAB() {
        val fab = binding.floatingActionButton

        fab.setOnClickListener {

            val intent = Intent(this, FormProdutoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configRecyclerView() {
        val lista = binding.lista

        lista.adapter = adapter

        adapter.clickNoItemListener = {

            val intent = Intent(
                this,
                DetalheProdutoActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }
}