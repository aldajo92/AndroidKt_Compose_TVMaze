package com.aldajo92.tvmazeapp.network.home

import com.squareup.moshi.Json

data class ShowDTO(
    val id: Long,
    val url: String?,
    val name: String,
    val type: String?,
    val language: String?,
    val genres: List<String>?,
    val status: String?,
    val runtime: Int?,
    val premiered: String?,
    val officialSite: String?,
    @Json(name = "network") val airChannel: ChannelDTO?,
    val webChannel: ChannelDTO?,
    val image: Map<String, String>?,
    @Json(name = "externals") val externalInfo: ExternalInfoDTO?,
    val summary: String?,
    val rating: Map<String, String>?
)

data class ChannelDTO(
    val id: Long?,
    val name: String?,
    val country: CountryDTO?
)

data class CountryDTO(
    val name: String,
    val code: String,
    val timezone: String
)

data class ExternalInfoDTO(
    val tvrage: String?,
    val thetvdb: Long?,
    val imdb: String?
)
