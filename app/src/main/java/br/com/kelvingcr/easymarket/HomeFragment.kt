package br.com.kelvingcr.easymarket

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.kelvingcr.easymarket.databinding.FragmentHomeBinding
import br.com.kelvingcr.easymarket.model.CompraModel
import br.com.kelvingcr.easymarket.viewmodel.CompraViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var compraViewModel: CompraViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        compraViewModel = ViewModelProvider(this)[CompraViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        observer()
        binding.tvPreco.animate().rotation(2000f).setDuration(3000).start()
        Handler().postDelayed({
            binding.tvPreco.animate().rotation(0f).setDuration(0).start()
        }, 2001)

        return root
    }

    fun observer() {
        compraViewModel.allbuyPrice.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.tvPreco!!.text = "$it"
        })
    }
    override fun onResume() {
        super.onResume()
        compraViewModel.getAllBuyPrice()
    }


}




