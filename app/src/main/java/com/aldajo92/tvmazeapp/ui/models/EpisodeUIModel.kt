package com.aldajo92.tvmazeapp.ui.models

data class EpisodeUIModel(
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
