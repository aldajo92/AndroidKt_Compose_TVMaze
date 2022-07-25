package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.network.home.ScheduleDTO
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
    this.summary.orEmpty(),
    this.rating?.get("average")?.toFloat(),
    this.schedule.toStringFormatted()
)

fun ScheduleDTO.toStringFormatted() =
    if (this.days.isNotEmpty()) "${this.days.toReadableDays()} ${this.time.validateEmptyTime()}" else "[No schedule available]"

fun String.validateEmptyTime() = if(this.isEmpty()) ": No hour available" else ": ${this.scheduleHourFormat()}"

private fun String.scheduleHourFormat() = this.ifEmpty { "" }

fun List<String>.toReadableDays() = this.map { item ->
    "${item.subSequence(0, 3)}"
}.reduce { acc, s -> "$acc, $s" }
