package com.example.arthur.bancodesenhas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SenhaAuxiliar(private val context: Context, private val senhasList: MutableList<Senhas>) : BaseAdapter() {

    override fun getCount(): Int {
        return senhasList.size
    }

    override fun getItem(position: Int): Any {
        return senhasList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val convertView = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_lista_de_senha, parent, false)

        val descricao: TextView = convertView.findViewById(R.id.DescricaoSenha)
        val tamanho: TextView = convertView.findViewById(R.id.TamanhoDaSenha)
        val senha = getItem(position) as Senhas

        descricao.text = senha.nomeS
        tamanho.text = "(${senha.tamanho})"

        return convertView
    }
}

