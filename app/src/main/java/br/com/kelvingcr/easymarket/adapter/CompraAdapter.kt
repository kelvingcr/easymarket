package br.com.kelvingcr.easymarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.kelvingcr.easymarket.CompraListener
import br.com.kelvingcr.easymarket.R
import br.com.kelvingcr.easymarket.adapter.holder.CompraHolder
import br.com.kelvingcr.easymarket.model.CompraModel

class CompraAdapter : RecyclerView.Adapter<CompraHolder>() {

    private var mCompraList: List<CompraModel> = arrayListOf()
    private lateinit var mListener: CompraListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompraHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.cliente_adapter, parent, false)
        return CompraHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: CompraHolder, position: Int) {
        holder.bind(mCompraList[position])
    }

    override fun getItemCount() = mCompraList.count()

    fun updateCompras(list: List<CompraModel>) {
        mCompraList = list
        notifyDataSetChanged()
    }


    fun attachListener(listener: CompraListener) {
        mListener = listener
    }
}