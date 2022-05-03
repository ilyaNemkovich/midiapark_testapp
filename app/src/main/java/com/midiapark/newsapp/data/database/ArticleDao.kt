package com.midiapark.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.midiapark.newsapp.data.database.entity.ArticleHeadlineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article ORDER BY ROWID DESC")
    fun getAll(): Flow<List<ArticleHeadlineEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(list: List<ArticleHeadlineEntity>)
}