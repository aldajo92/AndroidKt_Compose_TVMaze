package com.aldajo92.tvmazeapp.network.home

import com.squareup.moshi.Json
import org.jetbrains.annotations.Async

data class ShowDTO(
    val id: String,
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
    val rating: Map<String, String>?,
    val schedule: ScheduleDTO
)

data class ChannelDTO(
    val id: Long?,
    val name: String?,
    val country: CountryDTO?
)

data class ScheduleDTO(
    val time: String,
    val days: List<String>
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
