package com.example.maksim.stmp


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.maksim.stmp.kr1.Kr1Fragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDrawer()
        initFirstFragment(savedInstanceState)
    }

    private fun initFirstFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            return
        } else {
            setFragment(Lab1Fragment(), backStack = false)
        }
    }

    private fun initDrawer() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_lab1) {
            setFragment(Lab1Fragment())
        } else if (id == R.id.nav_lab2) {
            setFragment(Lab2Fragment())
        } else if (id == R.id.nav_lab3) {
            setFragment(Lab3Fragment())
        } else if (id == R.id.nav_kr1) {
            setFragment(Kr1Fragment())
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun setFragment(fragment: Fragment, backStack: Boolean = true) {
        Log.i(TAG, "Setting fragment: $fragment")
        val t = supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
        if (backStack) t.addToBackStack(fragment.javaClass.name)
        t.commit()
    }
}
