package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormProdutoBinding
import com.example.orgs.databinding.FormularioAlertDialogBinding
import com.example.orgs.extensions.carregarImagem
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastro de Produto"
        configBotao()

        binding.activityFormProdutoImagem.setOnClickListener {
            val bindingFormularioImagem = FormularioAlertDialogBinding.inflate(layoutInflater)
            val imagem = bindingFormularioImagem.imagemAlert

            bindingFormularioImagem.formularioImagemTextInputEditText.setText(url)
            imagem.carregarImagem(url)

            bindingFormularioImagem.btnCarregar.setOnClickListener {

                url = bindingFormularioImagem.formularioImagemTextInputEditText.text.toString()
                imagem.carregarImagem(url)
            }
            AlertDialog.Builder(this)
                .setView(bindingFormularioImagem.root)
                .setPositiveButton("Confirmar") { _, _ ->

                    binding.activityFormProdutoImagem.carregarImagem(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }

    fun configBotao() {
        val botao = binding.ButtonSalvar
        val db = AppDatabase.instancia(this)

        val produtoDao = db.produtoDao()

        botao.setOnClickListener {
            val novoProduto = criaProduto()

            produtoDao.salva(novoProduto)

            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.EditTextNome
        val nome = campoNome.text.toString()
        val campoDesc = binding.EditTextDesc
        val desc = campoDesc.text.toString()
        val campoValor = binding.EditTextValor
        val valorEmTexto = campoValor.text.toString()

        val valorNum = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        val novoProduto = Produto(
            nome = nome,
            descricao = desc,
            valor = valorNum,
            imagem = url
        )

        return novoProduto
    }

}

