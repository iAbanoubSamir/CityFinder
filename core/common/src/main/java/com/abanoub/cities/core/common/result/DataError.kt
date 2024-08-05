package com.abanoub.cities.core.common.result

sealed interface DataError : Error {
    enum class Local : DataError {
        FILE_NOT_FOUND,
        IO_EXCEPTION,
        JSON_PARSING_ERROR,
        DATA_CONVERSION_ERROR,
        UNEXPECTED_DATA_FORMAT,
        UNKNOWN
    }
}