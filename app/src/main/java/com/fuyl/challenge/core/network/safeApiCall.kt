package com.fuyl.challenge.core.network

import com.fuyl.challenge.core.model.Result
import com.fuyl.challenge.core.model.exception.toAppException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(
    errorMessage: String,
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.Error(errorMessage, e.toAppException())
    }
}