package com.rs.locprompt

import android.app.Application
import android.content.Context

/**
 * Created by ranjeetsinha on 17/02/18.
 */

class LocPrompt : Application() {

    companion object {
        //TODO(RS) : Handle this in a better way;

        var context: Context? = null;
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext;

    }

}