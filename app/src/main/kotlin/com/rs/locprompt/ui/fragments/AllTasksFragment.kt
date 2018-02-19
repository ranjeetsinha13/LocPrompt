package com.rs.locprompt.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rs.locprompt.R

/**
 * Created by ranjeetsinha on 19/02/18.
 */
class AllTasksFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.all_tasks_fragment, container, false)

    }
}