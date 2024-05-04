package com.example.smartloop

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.smartloop.databinding.ActivityHomeBinding
import com.example.smartloop.fragments.CourseFragment
import com.example.smartloop.fragments.HomeFragment
import com.example.smartloop.fragments.ProfileFragment
import com.example.smartloop.fragments.SearchFragment
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Smart"
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
            R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)


//        binding.bottomNavigation.background = null

        binding.bottomNavigation.setOnItemSelectedListener {
                item -> when(item.itemId){
            R.id.nav_bottom_home -> openFragment(HomeFragment())
            R.id.nav_bottom_course -> openFragment(CourseFragment())
            R.id.nav_bottom_search -> openFragment(SearchFragment())
            R.id.nav_bottom_profile -> openFragment(ProfileFragment())
        }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_side_home -> openFragment(HomeFragment())
            R.id.nav_side_course -> openFragment(CourseFragment())
            R.id.nav_side_search -> openFragment(SearchFragment())
            R.id.nav_side_profile -> openFragment(ProfileFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
        super.onBackPressed()
    }


    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }


}