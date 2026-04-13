package com.fuyl.challenge.core.model.exception

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException

/**
 * Base exception for all network-related errors in the app.
 * Inheriting from IOException is good practice for network issues.
 */
sealed class AppException(
    message: String, 
    cause: Throwable? = null
) : IOException(message, cause)

/**
 * Thrown when the server returns a 5xx error or an unexpected 3xx redirect.
 * This implies the issue is on the backend's side, not the user's app.
 */
class ServerException(
    message: String, 
    cause: Throwable? = null
) : AppException(message, cause)

/**
 * Thrown when the server returns a 4xx error (e.g., 401 Unauthorized, 404 Not Found).
 * This implies the app sent an invalid request or invalid credentials.
 */
class ClientException(
    message: String, 
    cause: Throwable? = null
) : AppException(message, cause)

/**
 * Thrown when an unexpected error occurs, like a lack of internet connection,
 * a timeout, or a JSON parsing failure.
 */
class UnknownNetworkException(
    message: String, 
    cause: Throwable? = null
) : AppException(message, cause)

fun Throwable.toAppException(): AppException = when (this) {
    is RedirectResponseException -> ServerException(message, this)
    is ServerResponseException -> ServerException(message, this)
    is ClientRequestException -> ClientException(message, this)
    is AppException -> this
    else -> UnknownNetworkException(message ?: "Unknown network error", this)
}