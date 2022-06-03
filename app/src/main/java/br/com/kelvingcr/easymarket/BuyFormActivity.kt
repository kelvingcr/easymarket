package br.com.kelvingcr.easymarket

import android.R
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import br.com.kelvingcr.easymarket.databinding.ActivityBuyFormBinding
import br.com.kelvingcr.easymarket.model.CompraModel
import br.com.kelvingcr.easymarket.model.ItemModel
import br.com.kelvingcr.easymarket.utils.AlertDialogListener
import br.com.kelvingcr.easymarket.utils.Utils
import br.com.kelvingcr.easymarket.viewmodel.CompraViewModel
import java.text.NumberFormat
import java.util.*

class BuyFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityBuyFormBinding

    private var lista = ArrayList<ItemModel>()
    private lateinit var adapter: ArrayAdapter<ItemModel>
    private lateinit var compra: CompraModel

    private lateinit var compraViewModel: CompraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compraViewModel = ViewModelProvider(this)[CompraViewModel::class.java]
        binding = ActivityBuyFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnItem.setOnClickListener(this)
        binding.btnCompra.setOnClickListener(this)
        binding.flotPriceCheck.setOnClickListener(this)

        configAdapter()
        removeItemInList()

        disableBarColorAndDarkTheme()

    }

    override fun onClick(view: View) {

        if(view.id == binding.btnItem.id) {

            val nome = binding.edtProduto.text.toString()
            val preco = binding.edtPreco.text.toString()
            if (!nome.isEmpty()) {
                if (!preco.isEmpty()) {
                    addItem(nome, preco.toDouble())
                } else {
                    binding.edtPreco.requestFocus()
                    binding.edtPreco.error = "Campo obrigátorio."
                }
            }else {
                binding.edtProduto.requestFocus()
                binding.edtProduto.error = "Campo obrigátorio."
            }

        } else if(view.id == binding.btnCompra.id) {

            var nomeCompra = binding.edtCompra.text.toString()

            if(lista.isNotEmpty()) {
                if (!nomeCompra.isEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                        compra = CompraModel(0, nomeCompra, lista, Utils.dataAtual(Date()))

                        Utils.AlertDialogBuilderSimple("Confirmação", "Deseja salvar essa compra? Total de itens: ${lista.count()}, Subtotal: ${getSubTotal(lista)}", this, object :
                            AlertDialogListener {
                            override fun onPressPositiveButton() { compraViewModel.save(compra)
                                finish()
                            }
                            override fun onPressNeutralButton() {}
                        }, "Salvar", "Voltar")

                    }
                } else {
                    binding.edtCompra.requestFocus()
                    binding.edtCompra.error = "Campo obrigátorio."
                }

            } else {
                Utils.AlertDialogBuilderSimple("Nenhum item encontrado", "Essa compra não será salva, nenhum item foi encontrado! ", this, object :
                    AlertDialogListener {
                    override fun onPressPositiveButton() { finish() }
                    override fun onPressNeutralButton() {}
                }, "Finalizar", "Voltar")
            }
        } else if (view.id == binding.flotPriceCheck.id) {

            if(lista.isNotEmpty()) {
                Toast.makeText(this, "Você ja gastou: " + getSubTotal(lista), Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this, "Nenhum item foi encontrado.", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun removeItemInList() {

        //Remove um item da lista ao clicar
        binding.listView.setOnItemClickListener { parent, view, position, id ->

            val element = adapter.getItem(position)
            lista.remove(element)
            adapter.notifyDataSetChanged()

            println(lista.size)
        }
    }

    fun addItem(nome: String, preco: Double) {
        val item = ItemModel(nome, preco)
        lista.add(item)
        adapter.notifyDataSetChanged()
    }

    fun configAdapter() {

        adapter = ArrayAdapter(this, R.layout.simple_list_item_1, lista)
        binding.listView.adapter = adapter

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(!lista.isEmpty()) {

                Utils.AlertDialogBuilderSimple("Itens pendentes", "Todos os itens adicionados serão perdidos, deseja mesmo continuar? ", this, object :
                    AlertDialogListener {
                    override fun onPressPositiveButton() { finish() }
                    override fun onPressNeutralButton() {}

                })
                return true
            } else {
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun disableBarColorAndDarkTheme() {

        //Desativa a rotação de tela
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        //Desativa o tema escuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Bar transparente
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

    }


   private fun getSubTotal(list: ArrayList<ItemModel>) : String {
         return NumberFormat.getCurrencyInstance().format(list.sumOf { it.valor })
    }

}