package com.rs.locprompt.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.rs.locprompt.entity.Reminder

/**
 * Created by ranjeetsinha on 17/02/18.
 */


@Dao
internal interface ReminderDao {
    @Query("SELECT * FROM reminders WHERE reminderId LIKE :reminderId")
    fun findById(reminderId: String): LiveData<Reminder>

    @Query("SELECT * FROM reminders")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Query("SELECT * FROM reminders WHERE status IS :status")
    fun getReminderByStatus(status: Int): LiveData<List<Reminder>>

    @Insert
    fun insert(reminder: Reminder)

    @Update
    fun update(reminder: Reminder)

    @Delete
    fun delete(reminder: Reminder)

}