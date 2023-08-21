package com.example.orgs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Produto (
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null

) : Parcelable {
    companion object {
        val valor: BigDecimal? = null
        val descricao: String = ""
        val nome: String = ""
    }
}