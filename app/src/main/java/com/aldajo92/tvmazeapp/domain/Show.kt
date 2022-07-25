package com.aldajo92.tvmazeapp.domain

data class Show(
    val id: String = "00",
    val url: String = "",
    val name: String = "",
    val type: String = "",
    val language: String = "",
    val genres: List<String> = listOf(),
    val status: String = "",
    val premiered: String = "",
    val officialSite: String = "",
    val imageMediumURL: String = "",
    val imageHighURL: String = "",
    val summary: String = "",
    val raiting: Float? = 0f,
    val scheduleText: String = ""
)
