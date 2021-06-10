package com.dicoding.expert.core.data.source.remote.network

sealed class APIRespon<out R> {
    data class Success<out T>(val data: T) : APIRespon<T>()
    data class Error(val errorMessage: String) : APIRespon<Nothing>()
    object Empty : APIRespon<Nothing>()
}