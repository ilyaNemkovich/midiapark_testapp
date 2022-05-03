package com.midiapark.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.midiapark.newsapp.data.database.entity.SearchHistoryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM history ORDER BY ROWID DESC")
    fun getAll(): Flow<List<SearchHistoryItemEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: SearchHistoryItemEntity)
}