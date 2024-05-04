package com.senior.d_text.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senior.d_text.data.model.notification.ReceiveNotification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHistory(receiveNotification: ReceiveNotification)

    @Query("SELECT * FROM receive_notification")
    fun getHistory(): Flow<List<ReceiveNotification>>

    @Query("DELETE FROM receive_notification")
    fun deleteAllHistory()

    @Query("DELETE FROM receive_notification WHERE id = (SELECT id FROM receive_notification ORDER BY id LIMIT 1)")
    fun deleteFirstHistory()
}