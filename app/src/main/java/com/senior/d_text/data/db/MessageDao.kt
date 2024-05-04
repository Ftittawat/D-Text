package com.senior.d_text.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senior.d_text.data.model.message.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMessage(message: Message)

    @Query("SELECT * FROM message")
    fun getMessage(): Flow<List<Message>>

    @Query("DELETE FROM message")
    fun deleteAllMessage()

}