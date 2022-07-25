package com.aldajo92.tvmazeapp.domain

data class Episode(
    val id: String = "00",
    val url: String = "",
    val name: String = "",
    val season: Int = 0,
    val number: Int = 0,
    val summary: String = "",
    val airdate: String = "",
    val airtime: String = "",
    val runtime: Int = 0,
    val imageMediumURL: String = "",
    val imageHighURL: String = ""
)
