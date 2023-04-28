package com.flow.booksearch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flow.booksearch.data.model.Book
import com.flow.booksearch.data.model.RecentKeyword

@Database(
    entities = [RecentKeyword::class, Book::class],
    version = 1,
    exportSchema = false
)
abstract class BookSearchDatabase : RoomDatabase(){

    abstract fun keywordDao() : KeywordDao
    abstract fun bookMark() : BookMarkDao
}