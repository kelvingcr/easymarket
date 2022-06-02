package br.com.kelvingcr.easymarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.kelvingcr.easymarket.model.CompraModel
import br.com.kelvingcr.easymarket.model.ItemModel
import br.com.kelvingcr.easymarket.repository.CompraRepository
import java.text.NumberFormat

class CompraViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataBaseCompra = CompraRepository(application.applicationContext)

    private val mCompraList = MutableLiveData<List<CompraModel>>()
    val compraList: LiveData<List<CompraModel>> = mCompraList

    private val mAllBuyPrice = MutableLiveData<String>()
    val allbuyPrice: LiveData<String> = mAllBuyPrice

    fun load() {
        mCompraList.value = mDataBaseCompra.getAll()
    }

    fun get(id: Int) : CompraModel {
        return mDataBaseCompra.get(id)
    }

    fun getAll() : List<CompraModel> {
        return mDataBaseCompra.getAll()
    }

    fun save(compra: CompraModel) : Boolean {
       return mDataBaseCompra.save(compra)
    }

    fun delete(id: Int) : Boolean {
        return mDataBaseCompra.delete(id)
    }

    fun getAllBuyPrice()  {
        val list = mDataBaseCompra.getAll()
        val itens = ArrayList<ItemModel>()
        list.forEach { it.itens.forEach { itens.add(it) } }
        mAllBuyPrice.value = NumberFormat.getCurrencyInstance().format(itens.sumOf { it.valor })

    }

}