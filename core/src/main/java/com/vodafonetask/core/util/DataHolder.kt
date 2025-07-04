package com.vodafonetask.core.util

sealed class DataHolder<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Success<T>(data: T) : DataHolder<T>(data = data)
    class Failure<T>(error: String) : DataHolder<T>(error = error)
}