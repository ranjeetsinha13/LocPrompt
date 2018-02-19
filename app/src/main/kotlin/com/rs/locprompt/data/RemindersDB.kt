package com.rs.locprompt.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.rs.locprompt.dao.ReminderDao
import com.rs.locprompt.entity.Reminder

/**
 * Created by ranjeetsinha on 17/02/18.
 */
@Database(entities = [(Reminder::class)], version = 1, exportSchema = false)
internal abstract class RemindersDB : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao

}