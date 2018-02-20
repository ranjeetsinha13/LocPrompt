package com.rs.locprompt.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import com.androidifygeeks.library.fragment.PageFragment
import com.androidifygeeks.library.fragment.TabDialogFragment
import com.androidifygeeks.library.iface.IFragmentListener
import com.androidifygeeks.library.iface.ISimpleDialogCancelListener
import com.androidifygeeks.library.iface.ISimpleDialogListener
import com.rs.locprompt.R
import com.rs.locprompt.utility.Constants
import kotlinx.android.synthetic.main.add_task_activity.*
import kotlin.math.roundToInt


/**
 * Created by ranjeetsinha on 19/02/18.
 */
class AddTaskActivity : BaseActivity(), ISimpleDialogListener, ISimpleDialogCancelListener, IFragmentListener {


    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var locationAddress: String = ""

    private var reminderTime: String = ""


    companion object {
        private const val REQUEST_LOCATION: Int = 24
        private const val REQUEST_TIME: Int = 25

    }

    override val TAG: String = AddTaskActivity::class.java.simpleName

    override fun getLayout(): Int = R.layout.add_task_activity

    override fun getActivityTitle(): Int = R.string.add_task_title

    override fun onNeutralButtonClicked(requestCode: Int) {
    }

    override fun onPositiveButtonClicked(requestCode: Int) {
    }

    override fun onNegativeButtonClicked(requestCode: Int) {
    }

    override fun onCancelled(requestCode: Int) {
    }

    override fun onFragmentViewCreated(fragment: Fragment?) {

        val selectedTabPosition = fragment!!.arguments!!.getInt(PageFragment.ARG_DAY_INDEX, 0)
        val rootContainer = fragment.view!!.findViewById<FrameLayout>(R.id.root_container)
        Log.i(TAG, "Position: " + selectedTabPosition)
        when (selectedTabPosition) {
            0 -> {
                val view = layoutInflater.inflate(R.layout.date_picker_layout, rootContainer as ViewGroup)
            }
            1 -> {
                layoutInflater.inflate(R.layout.time_picker_layout, rootContainer as ViewGroup)
            }
        }
    }

    override fun onFragmentAttached(fragment: Fragment?) {
    }

    override fun onFragmentDetached(fragment: Fragment?) {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskDescription = findViewById<EditText>(R.id.taskDescription)
        taskDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                add.isEnabled = true
            }


        })


        //taskDescription.text
        setLocation.setOnClickListener({
            // Open Map
            startActivityForResult(Intent(this, MapsActivity::class.java), REQUEST_LOCATION)
        })

        setDate.setOnClickListener({
            // Set Date and Time
            handleDatePicker()
        })

        add.setOnClickListener({
            // Save in Database
            // Check if date; location and text are not empty;

        })

        cancel.setOnClickListener({
            onBackPressed()
        })


    }

    private fun handleDatePicker() {
        TabDialogFragment.createBuilder(this@AddTaskActivity, supportFragmentManager)
                .setContentPaneHeight((windowManager.defaultDisplay.height * 0.55).roundToInt())
                .setTabButtonText(arrayOf<CharSequence>(getString(R.string.date), getString(R.string.time)))
                .setTitle(getString(R.string.set_time_title))
                .setPositiveButtonText(getString(R.string.ok))
                .setNegativeButtonText(getString(R.string.cancel_button))
                .setRequestCode(REQUEST_TIME).useLightTheme()
                .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_LOCATION -> {
                if (resultCode == Activity.RESULT_OK) {
                    Log.i(TAG, data!!.getStringExtra(Constants.PLACE_NAME).toString())
                    latitude = data.getDoubleExtra(Constants.LATITUDE, 0.0)
                    longitude = data.getDoubleExtra(Constants.LONGITUDE, 0.0)
                    locationAddress = data.getStringExtra(Constants.PLACE_NAME)
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    // Do nothing;
                }

            }
            REQUEST_TIME -> {

            }

        }
    }


}