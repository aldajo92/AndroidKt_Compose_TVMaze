package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.domain.Episode
import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.ui.models.EpisodeUIModel

fun Episode.toUIModel() = EpisodeUIModel(
    id = this.id,
    url = this.url,
    name = this.name,
    season = this.season,
    number = this.number,
    summary = this.summary,
    airdate = this.airdate,
    airtime = this.airtime,
    runtime = this.runtime,
    imageMediumURL = this.imageMediumURL,
    imageHighURL = this.imageHighURL
)

fun EpisodeDTO.toDomainModel() = Episode(
    this.id,
    this.url.orEmpty(),
    this.name.orEmpty(),
    this.season ?: 0,
    this.number ?: 0,
    this.summary.orEmpty(),
    this.airdate.orEmpty(),
    this.airtime.orEmpty(),
    this.runtime ?: 0,
    this.image?.get("medium").orEmpty(),
    this.image?.get("original").orEmpty()
)
