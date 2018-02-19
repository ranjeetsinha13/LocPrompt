package com.rs.locprompt.ui

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import com.journaler.permission.PermissionRequestCallback
import com.rs.locprompt.R
import com.rs.locprompt.ui.fragments.AllTasksFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    override val TAG: String = MainActivity::class.java.name

    override fun getLayout(): Int = R.layout.activity_main

    override fun getActivityTitle(): Int = R.string.app_name

    private val permissionRequestCallback = object : PermissionRequestCallback {
        override fun onPermissionGranted(permissions: List<String>) {
            Log.i(TAG, "Permission granted [ $permissions ]")
        }

        @TargetApi(Build.VERSION_CODES.M)
        override fun onPermissionDenied(permissions: List<String>) {
            Log.e(TAG, "Permission denied [ $permissions ]")

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show Rationale
                Log.i(TAG, "Displaying permission rationale to provide additional context.")
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.permission_rationale),
                        Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, {
                    requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION, callback =
                    this)
                }).show()
            } else {
                Snackbar.make(findViewById(android.R.id.content),
                        getText(R.string.permission_denied_explanation),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(resources.getString(R.string.settings), {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts("package", packageName, null)
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                            startActivity(intent)
                        }).show()


            }


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request Needed Permissions;
        requestPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, callback = permissionRequestCallback)

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
