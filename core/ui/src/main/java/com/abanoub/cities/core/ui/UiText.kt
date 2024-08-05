package com.abanoub.cities.core.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * A sealed class representing different types of text that can be displayed in the UI.
 */
sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> LocalContext.current.getString(id, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}