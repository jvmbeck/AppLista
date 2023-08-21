package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.carregarImagem(url: String? = null) {
    load(url) {
        //caso imagem nula
        fallback(R.drawable.giuseppe)
        error(R.drawable.erro)

        //imagem de carregamento
        placeholder(R.drawable.placeholder)

    }
}