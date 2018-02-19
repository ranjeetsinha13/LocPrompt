package com.rs.locprompt.ui

import android.os.Bundle
import com.rs.locprompt.permission.PermissionCompatActivity
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * Created by ranjeetsinha on 19/02/18.
 */
abstract class BaseActivity : PermissionCompatActivity() {

    protected abstract val TAG: String

    protected abstract fun getLayout(): Int

    protected abstract fun getActivityTitle(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setSupportActionBar(toolbar)
        setTitle(getActivityTitle())


    }


}