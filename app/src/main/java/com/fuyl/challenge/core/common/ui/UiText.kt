package com.fuyl.challenge.core.common.ui

import android.R
import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class StringResource(@StringRes val resId: Int, val args: List<Any> = emptyList()) : UiText()
    data class RawString(val value: String) : UiText()
    data class ExceptionMessage(val exception: Throwable) : UiText()

    fun resolve(context: Context): String = when (this) {
        is StringResource -> context.getString(resId, *args.toTypedArray())
        is RawString -> value
        is ExceptionMessage -> exception.message ?: context.getString(R.string.unknownName)
    }
}