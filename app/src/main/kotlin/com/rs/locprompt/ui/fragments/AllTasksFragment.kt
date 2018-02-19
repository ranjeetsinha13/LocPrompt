package com.rs.locprompt.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.rs.locprompt.R
import com.rs.locprompt.ui.AddTaskActivity
import kotlinx.android.synthetic.main.all_tasks_fragment.*

/**
 * Created by ranjeetsinha on 19/02/18.
 */
class AllTasksFragment : BaseFragment() {
    override val TAG: String = AllTasksFragment::class.java.simpleName

    override fun getLayout(): Int = R.layout.all_tasks_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            //Start Add Task Activity;
            startActivity(Intent(context, AddTaskActivity::class.java))
        }

    }
}