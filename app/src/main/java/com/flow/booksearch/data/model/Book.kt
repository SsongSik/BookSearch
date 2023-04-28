package com.flow.booksearch.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "books")
data class Book(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: Int,
    val publisher: String,
    val pubdate: String,
    @PrimaryKey(autoGenerate = false)
    val isbn: String,
    val description: String,
) : Parcelable
