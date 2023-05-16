package com.application.entities

sealed class ObjectResult<out T> {
    data class Success<T>(val data: T) : ObjectResult<T>()
    data class Completed(val completed: Boolean) : ObjectResult<Nothing>()
    data class Failure(val exception: Exception) : ObjectResult<Nothing>()
}