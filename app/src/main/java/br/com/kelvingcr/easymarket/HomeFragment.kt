package br.com.kelvingcr.easymarket

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.kelvingcr.easymarket.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvPreco.animate().rotation(2000f).setDuration(3000).start()
        Handler().postDelayed({
            binding.tvPreco.animate().rotation(0f).setDuration(0).start()
        }, 2001)

        return root
    }




}