package com.aldajo92.tvmazeapp.database.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_by_show")
data class EpisodeEntity constructor(
    @PrimaryKey
    val id: String,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val summary: String,
    val airdate: String,
    val airtime: String,
    val runtime: Int,
    val imageMediumURL: String,
    val imageHighURL: String
)
