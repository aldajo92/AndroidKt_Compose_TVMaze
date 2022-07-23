package com.aldajo92.tvmazeapp.network.home

data class EpisodeDTO(
    val id: String,
    val url: String?,
    val name: String?,
    val season: Int?,
    val number: Int?,
    val airdate: String?,
    val airtime: String?,
    val runtime: Int?,
    val image: Map<String, String>?
)
