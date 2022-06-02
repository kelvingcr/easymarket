package br.com.kelvingcr.easymarket.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.kelvingcr.easymarket.CompraListener
import br.com.kelvingcr.easymarket.R
import br.com.kelvingcr.easymarket.Utils
import br.com.kelvingcr.easymarket.model.CompraModel

class CompraHolder(itemView: View, private val listener: CompraListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(compra: CompraModel) {

        val textName = itemView.findViewById<TextView>(R.id.tvName)
        textName.text = compra.nome

        val tvPreco = itemView.findViewById<TextView>(R.id.tvPreco)
        tvPreco.text = "R" + Utils.formatNumber(compra.itens.sumOf { it.valor })

        val tvData = itemView.findViewById<TextView>(R.id.tvData)
        tvData.text = compra.data

        val cvClientes = itemView.findViewById<CardView>(R.id.cvClientes)

        cvClientes.setOnClickListener {
            listener.onClick(compra.id)
        }
    }


}