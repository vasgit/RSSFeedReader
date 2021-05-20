package com.test.rssfeedreader.database.exceptions

class NotFoundException(
    message: String = "",
    cause: Throwable? = null
) : Exception(message, cause)