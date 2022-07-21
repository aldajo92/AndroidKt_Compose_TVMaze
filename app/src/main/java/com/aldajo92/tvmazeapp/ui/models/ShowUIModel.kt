package com.aldajo92.tvmazeapp.ui.models

data class ShowUIModel(
    val id: String,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val premiered: String,
    val officialSite: String,
    val imageMediumURL: String,
    val imageHighURL: String,
    val summary: String
)
