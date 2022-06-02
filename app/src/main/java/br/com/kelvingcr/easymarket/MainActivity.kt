package br.com.kelvingcr.easymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import br.com.kelvingcr.easymarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        disableBarColorAndDarkTheme()
        loadFragments()
        setFragment(homeFragment)

        binding.chipNavigationBar.setItemSelected(R.id.menuclip_home, true)

    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragLayout.id, fragment)
        fragmentTransaction.commit()
    }

    private fun loadFragments() {
        homeFragment = HomeFragment()
    }

    private fun disableBarColorAndDarkTheme() {

        //Desativa o tema escuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Bar transparente
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

    }

}