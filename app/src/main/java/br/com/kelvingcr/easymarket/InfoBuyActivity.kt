package br.com.kelvingcr.easymarket

import android.R
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import br.com.kelvingcr.easymarket.databinding.ActivityInfoBuyBinding
import br.com.kelvingcr.easymarket.model.CompraModel
import br.com.kelvingcr.easymarket.model.ItemModel
import br.com.kelvingcr.easymarket.utils.AlertDialogListener
import br.com.kelvingcr.easymarket.utils.Utils
import br.com.kelvingcr.easymarket.viewmodel.CompraViewModel
import java.text.NumberFormat

class InfoBuyActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityInfoBuyBinding
    private lateinit var compra: CompraModel
    private lateinit var compraViewModel: CompraViewModel

    private lateinit var adapter: ArrayAdapter<ItemModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        compraViewModel = ViewModelProvider(this)[CompraViewModel::class.java]
        binding = ActivityInfoBuyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        disableBarColorAndDarkTheme()

        val ss:Int = intent.getIntExtra("compra_id", 0)
        compra = compraViewModel.get(ss)

        binding.flotButtonAdd.setOnClickListener(this)

        init()
    }

    private fun init() {
        binding.tvData.text = compra.data
        binding.tvNomeCompra.text = compra.nome

        val total = compra.itens.sumOf { it.valor }
        binding.tvValor.text =  NumberFormat.getCurrencyInstance().format(total)

        val list = compra.itens

        adapter = ArrayAdapter(this, R.layout.simple_list_item_1, list)
        binding.listView.adapter = adapter
    }

    override fun onClick(view: View) {
        Utils.AlertDialogBuilderSimple("Deletando compra", "Você deseja mesmo deletar essa compra?", this, object :
            AlertDialogListener {
            override fun onPressPositiveButton() {
                compraViewModel.delete(compra.id)
                finish()
            }

            override fun onPressNeutralButton() {
                finish()
            }

        })
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


}