package br.com.kelvingcr.easymarket.viewmodel.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.kelvingcr.easymarket.R
import br.com.kelvingcr.easymarket.databinding.FragmentAboutUsBinding
import br.com.kelvingcr.easymarket.databinding.FragmentAllBuysBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.txtDev.setOnClickListener {
            val uri = Uri.parse("https://beacons.ai/kelvingcr")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        return root
    }

}