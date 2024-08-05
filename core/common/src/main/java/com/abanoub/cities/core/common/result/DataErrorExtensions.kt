package com.abanoub.cities.core.common.result

import com.abanoub.cities.core.common.R
import com.abanoub.cities.core.ui.UiText

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.FILE_NOT_FOUND -> UiText.StringResource(
            R.string.file_not_found
        )
        DataError.Local.IO_EXCEPTION -> UiText.StringResource(
            R.string.io_exception
        )
        DataError.Local.JSON_PARSING_ERROR -> UiText.StringResource(
            R.string.json_parsing_error
        )
        DataError.Local.DATA_CONVERSION_ERROR -> UiText.StringResource(
            R.string.data_conversion_error
        )
        DataError.Local.UNEXPECTED_DATA_FORMAT -> UiText.StringResource(
            R.string.unexpected_data_format
        )
        DataError.Local.UNKNOWN -> UiText.StringResource(
            R.string.unknown_error
        )
    }
}

fun Result.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}