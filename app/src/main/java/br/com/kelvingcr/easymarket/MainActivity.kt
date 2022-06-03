package br.com.kelvingcr.easymarket

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import br.com.kelvingcr.easymarket.databinding.ActivityMainBinding
import br.com.kelvingcr.easymarket.viewmodel.fragments.AboutUsFragment
import br.com.kelvingcr.easymarket.viewmodel.fragments.AllBuysFragment
import br.com.kelvingcr.easymarket.viewmodel.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var allBuysFragment: AllBuysFragment
    private lateinit var aboutUsFragment: AboutUsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        disableBarColorAndDarkTheme()
        loadFragments()
        setFragment(homeFragment)

        configClicks()
        binding.chipNavigationBar.setItemSelected(R.id.menuclip_home, true)

    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragLayout.id, fragment)
        fragmentTransaction.commit()
    }

    private fun loadFragments() {
        homeFragment = HomeFragment()
        allBuysFragment = AllBuysFragment()
        aboutUsFragment = AboutUsFragment()
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

    private fun configClicks() {
        binding.chipNavigationBar.setOnItemSelectedListener {
            when (it) {
                R.id.menuclip_home -> {
                    setFragment(homeFragment)
                }
                R.id.menuclip_compras-> {
                    setFragment(allBuysFragment)
                }
                R.id.menuclip_sobre-> {
                    setFragment(aboutUsFragment)
                }
            }
        }
    }

}