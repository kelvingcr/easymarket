package br.com.kelvingcr.easymarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.kelvingcr.easymarket.model.CompraModel
import br.com.kelvingcr.easymarket.model.ItemModel
import br.com.kelvingcr.easymarket.repository.CompraRepository
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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

     fun getValueForDate() : ArrayList<CompraModel> {
        val listaFiltrada = ArrayList<CompraModel>()
        val list = getAll()
        val dates = HashMap<Int, Date>()

        list.forEach { dates.put(it.id, format(it.data)) }

        for(x in dates) {
            if(x.value.month == Date().month){
                listaFiltrada.add(get(x.key))
            }
        }

        return listaFiltrada
    }

    private fun format(data: String) : Date {
        val simpleDataFormat = SimpleDateFormat("dd/MM/yyyy")
        val dataFormatada = simpleDataFormat.parse(data)
        return dataFormatada
    }

}