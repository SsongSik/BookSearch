package com.flow.booksearch.data.db

import androidx.room.*
import com.flow.booksearch.data.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book : Book)

    @Query("SELECT * FROM books")
    fun getFavoriteBooks() : Flow<List<Book>>

    @Delete
    suspend fun deleteBook(book : Book)
}