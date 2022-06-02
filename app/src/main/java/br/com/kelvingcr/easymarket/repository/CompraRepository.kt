package br.com.kelvingcr.easymarket.repository

import android.content.Context
import br.com.kelvingcr.easymarket.model.CompraModel

class CompraRepository (context: Context) {

    private val mDataBase = CompraDatabase.getDatabase(context).compraDAO()

    fun getAll() : List<CompraModel> {
        return mDataBase.getAll()
    }

    fun get(id: Int) : CompraModel {
        return mDataBase.get(id)
    }

    fun save(compra: CompraModel) : Boolean {
        return mDataBase.save(compra) > 0
    }
    fun delete(id: Int) : Boolean {
        return mDataBase.delete(id) > 0
    }
}
