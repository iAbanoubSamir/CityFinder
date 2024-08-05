package com.abanoub.cities.core.common.result

/**
 * A subtype of [Error] representing errors related to data handling.
 */
sealed interface DataError : Error {
    /**
     * Represents local data errors that can occur during data processing.
     */
    enum class Local : DataError {
        FILE_NOT_FOUND,
        IO_EXCEPTION,
        JSON_PARSING_ERROR,
        DATA_CONVERSION_ERROR,
        UNEXPECTED_DATA_FORMAT,
        UNKNOWN
    }
}