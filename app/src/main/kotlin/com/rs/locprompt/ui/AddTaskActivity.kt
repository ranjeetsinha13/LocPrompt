package com.rs.locprompt.ui

import android.os.Bundle
import com.rs.locprompt.R

/**
 * Created by ranjeetsinha on 19/02/18.
 */
class AddTaskActivity : BaseActivity() {
    override val TAG: String = AddTaskActivity::class.java.simpleName

    override fun getLayout(): Int = R.layout.add_task_activity

    override fun getActivityTitle(): Int = R.string.add_task_title

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


}