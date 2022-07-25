package com.aldajo92.tvmazeapp.database.favorites_show

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aldajo92.tvmazeapp.domain.Show

@Entity
data class FavoriteShowEntity constructor(
    @PrimaryKey
    val id: String,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
//    val genres: List<String> = emptyList(),
    val status: String,
    val premiered: String,
    val officialSite: String,
    val imageMediumURL: String,
    val imageHighURL: String,
    val summary: String,
    val raiting: Float?,
    val scheduleText: String
)
