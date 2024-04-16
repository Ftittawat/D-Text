package com.senior.d_text.data.db

import android.database.Cursor
import androidx.room.Database
import androidx.room.RoomDatabase
import com.senior.d_text.data.model.history.History
import org.jetbrains.annotations.NotNull

@Database(entities = [History::class],
version = 1,
exportSchema = false)
abstract class DTextDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}