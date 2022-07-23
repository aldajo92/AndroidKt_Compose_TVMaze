package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.ui.models.EpisodeUIModel

fun EpisodeDTO.toUIModel() = EpisodeUIModel(
    this.id,
    this.url.orEmpty(),
    this.name.orEmpty(),
    this.season ?: 0,
    this.number ?: 0,
    this.airdate.orEmpty(),
    this.airtime.orEmpty(),
    this.runtime ?: 0,
    this.image?.get("medium").orEmpty(),
    this.image?.get("original").orEmpty()
)
