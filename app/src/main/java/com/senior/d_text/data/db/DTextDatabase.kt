package com.senior.d_text.data.db

import android.database.Cursor
import androidx.room.Database
import androidx.room.RoomDatabase
import com.senior.d_text.data.model.history.History
import com.senior.d_text.data.model.message.Message
import com.senior.d_text.data.model.notification.Notification
import org.jetbrains.annotations.NotNull

@Database(entities = [History::class, Message::class],
version = 1,
exportSchema = false)
abstract class DTextDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun messageDao(): MessageDao
}