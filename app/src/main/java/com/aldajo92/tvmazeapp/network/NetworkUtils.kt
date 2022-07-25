package com.aldajo92.tvmazeapp.network

import com.aldajo92.tvmazeapp.exceptions.TVMazeAppException
import retrofit2.Response

fun <T> Response<T>?.handleBodyResponse(): T =
    when (this?.code()) {
        null -> throw TVMazeAppException(errorMessage = "Null content")
        in 200..300 -> body() ?: throw TVMazeAppException(
            code(), message() ?: "Something wrong"
        )
        else -> throw TVMazeAppException(code(), message())
    }
