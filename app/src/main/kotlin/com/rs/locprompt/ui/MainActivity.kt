package com.rs.locprompt.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rs.locprompt.R
import com.rs.locprompt.ui.fragments.AllTasksFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Set initial Fragment;
        setHomeFragment()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val fragments = supportFragmentManager.backStackEntryCount
            if (fragments <= 1) {
                finish()
            } else {
                super.onBackPressed()
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentPopped = supportFragmentManager.popBackStackImmediate(fragment.javaClass.name, 0)
        if (!fragmentPopped && supportFragmentManager.findFragmentByTag(fragment.javaClass.name) == null) {
            supportFragmentManager.beginTransaction().replace(R.id.content_main, fragment,
                    fragment.javaClass.name).addToBackStack(fragment.javaClass.name).commit()
        }

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                setHomeFragment()
            }
            R.id.nav_about -> {

            }
            R.id.nav_settings -> {

            }
            R.id.nav_feedback -> {

            }
            R.id.nav_share -> {

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setHomeFragment() {
        var f: Fragment? = supportFragmentManager.findFragmentByTag(AllTasksFragment::class.java.name)
        if (f == null) {
            f = AllTasksFragment()
        }
        setFragment(f)
    }
}
