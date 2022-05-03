package com.midiapark.newsapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class SearchHistoryItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "item")
    val searchItem: String
)