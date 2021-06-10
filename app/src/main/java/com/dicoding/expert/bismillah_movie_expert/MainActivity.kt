package com.dicoding.expert.bismillah_movie_expert

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.dicoding.expert.bismillah_movie_expert.databinding.ActivityMainBinding
import com.dicoding.expert.bismillah_movie_expert.home.HomeFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMainMovie.tbMovie,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.nvMovie.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.nv_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when(item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
                title =  getString(R.string.app_name)
            }
            R.id.nav_favorite ->{
                val uri = Uri.parse("bismillah_movie_expert://favourite")
                startActivity(Intent(Intent.ACTION_VIEW,uri))
            }
        }
        if (fragment != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.nv_fragment,fragment)
                .commit()
        }
        supportActionBar?.title = title

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}