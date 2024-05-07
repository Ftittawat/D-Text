package com.senior.d_text.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senior.d_text.data.model.notification.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNotification(notification: Notification)

    @Query("SELECT * FROM notification")
    fun getNotification(): Flow<List<Notification>>

    @Query("DELETE FROM notification")
    fun deleteAllNotification()

    @Query("DELETE FROM notification WHERE id = :inputId")
    fun deleteNotification(inputId: Int)
}