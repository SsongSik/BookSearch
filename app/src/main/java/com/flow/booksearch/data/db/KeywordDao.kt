package com.flow.booksearch.data.db

import androidx.room.*
import com.flow.booksearch.data.model.RecentKeyword
import kotlinx.coroutines.flow.Flow

@Dao
interface KeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeyword(recentKeyword: RecentKeyword)

    @Query("SELECT * FROM keywords ORDER BY timestamp DESC LIMIT 10")
    fun getRecentKeyword(): Flow<List<RecentKeyword>>
}