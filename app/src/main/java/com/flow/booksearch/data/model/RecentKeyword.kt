package com.flow.booksearch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keywords")
data class RecentKeyword(
    @PrimaryKey(autoGenerate = true)
    val index: Int,
    val keyword: String,
    val timestamp: Long = System.currentTimeMillis()
)