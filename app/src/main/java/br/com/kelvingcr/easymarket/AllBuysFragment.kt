package br.com.kelvingcr.easymarket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.kelvingcr.easymarket.adapter.CompraAdapter
import br.com.kelvingcr.easymarket.databinding.FragmentAllBuysBinding
import br.com.kelvingcr.easymarket.viewmodel.CompraViewModel


class AllBuysFragment : Fragment() {

    private var _binding: FragmentAllBuysBinding? = null
    private val binding get() = _binding!!

    private lateinit var mListener: CompraListener
    private val mAdapter: CompraAdapter = CompraAdapter()

    private lateinit var compraViewModel: CompraViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        compraViewModel = ViewModelProvider(this)[CompraViewModel::class.java]
        _binding = FragmentAllBuysBinding.inflate(inflater, container, false)
        val root: View = binding.root
        configClicks()
        recyclerLoad()

        mListener = object : CompraListener {
            override fun onClick(id: Int) {
                var intent = Intent(context, InfoBuyActivity::class.java)
                intent.putExtra("compra_id", id)
                startActivity(intent)
            }
        }

        mAdapter.attachListener(mListener)
        return root

    }

    override fun onResume() {
        super.onResume()
        //Aqui ele carrega a lista de compras
        compraViewModel.load()

    }

    private fun observer() {
        compraViewModel.compraList.observe(viewLifecycleOwner) {
            mAdapter.updateCompras(it)
        }
    }

    fun recyclerLoad() {

        val layoutManagerr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //RecyclerView
        val recycler = binding.rvCompra

        //Definir layout
        // recycler.layoutManager = layoutManagerr
        recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        //Adapter
        recycler.adapter = mAdapter

        observer()

    }
    private fun configClicks() {
        binding.flotButtonAdd.setOnClickListener {
            startActivity(Intent(context, BuyFormActivity::class.java))
        }
    }

}