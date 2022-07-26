package com.aldajo92.tvmazeapp.mappers

import com.aldajo92.tvmazeapp.database.favorites_show.FavoriteShowEntity
import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.network.home.ScheduleDTO
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel

fun Show.toUIModel() = ShowUIModel(
    id = this.id,
    url = this.url,
    name = this.name,
    type = this.type,
    language = this.language,
    genres = listOf(),
    status = this.status,
    premiered = this.premiered,
    officialSite = this.officialSite,
    imageMediumURL = this.imageMediumURL,
    imageHighURL = this.imageHighURL,
    summary = this.summary,
    raiting = this.raiting,
    scheduleText = this.scheduleText,
    isFavorite = this.isFavorite
)

fun ShowDTO.toDomainModel() = Show(
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

fun String.validateEmptyTime() =
    if (this.isEmpty()) ": No hour available" else ": ${this.scheduleHourFormat()}"

private fun String.scheduleHourFormat() = this.ifEmpty { "" }

fun List<String>.toReadableDays() = this.map { item ->
    "${item.subSequence(0, 3)}"
}.reduce { acc, s -> "$acc, $s" }

fun List<FavoriteShowEntity>.asDomainModel(): List<Show> {
    return map {
        Show(
            id = it.id,
            url = it.url,
            name = it.name,
            type = it.type,
            language = it.language,
            genres = listOf(), // TODO: pending to handle genres here
            status = it.status,
            premiered = it.premiered,
            officialSite = it.officialSite,
            imageMediumURL = it.imageMediumURL,
            imageHighURL = it.imageHighURL,
            summary = it.summary,
            raiting = it.raiting,
            scheduleText = it.scheduleText,
            true
        )
    }
}

fun Show.asDatabaseModel(): FavoriteShowEntity =
    FavoriteShowEntity(
        id = this.id,
        url = this.url,
        name = this.name,
        type = this.type,
        language = this.language,
//        genres = listOf(), // TODO: pending to handle genres here
        status = this.status,
        premiered = this.premiered,
        officialSite = this.officialSite,
        imageMediumURL = this.imageMediumURL,
        imageHighURL = this.imageHighURL,
        summary = this.summary,
        raiting = this.raiting,
        scheduleText = this.scheduleText
    )
