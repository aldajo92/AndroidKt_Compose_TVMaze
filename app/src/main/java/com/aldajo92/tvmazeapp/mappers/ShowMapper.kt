package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel

fun ShowDTO.toUIModel() = ShowUIModel(
    this.id,
    this.url.orEmpty(),
    this.name,
    this.type.orEmpty(),
    this.language.orEmpty(),
    this.genres ?: listOf(),
    this.status.orEmpty(),
    this.premiered.orEmpty(),
    this.officialSite.orEmpty(),
    this.image?.get("medium").orEmpty(),
    this.image?.get("original").orEmpty(),
    this.summary.orEmpty()
)
