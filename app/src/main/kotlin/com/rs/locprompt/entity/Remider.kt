package com.rs.locprompt.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ranjeetsinha on 17/02/18.
 */


@Entity(tableName = "reminders")
data class Reminder(@PrimaryKey var reminderId: String = "",
                    var reminderTitle: String = "",
                    var reminderDetails: String = "",
                    var status: Int = 0,
                    var latitude: Double,
                    var longitude: Double,
                    var radius: Double)


