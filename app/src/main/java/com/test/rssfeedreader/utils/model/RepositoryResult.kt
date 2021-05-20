package com.test.rssfeedreader.utils.model

data class RepositoryResult<T>(
    var cacheResult: Result<T>? = null,
    var serverResult: Result<T>? = null,
    var databaseResult: Result<T>? = null
) {


    constructor(exception: Throwable): this(Result.Failure(exception))


    constructor(result: Result<T>): this(cacheResult = result)


    fun getSuccessfulResult(): Result.Success<T> {
        return when {
            isCacheResultSuccessful() -> (cacheResult as Result.Success<T>)
            isServerResultSuccessful() -> (serverResult as Result.Success<T>)
            else -> (databaseResult as Result.Success<T>)
        }
    }


    fun getSuccessfulResultValue(): T {
        return getSuccessfulResult().value
    }


    fun getErroneousResult(): Result.Failure {
        return when {
            isCacheResultErroneous() -> (cacheResult as Result.Failure)
            isServerResultErroneous() -> (serverResult as Result.Failure)
            else -> (databaseResult as Result.Failure)
        }
    }


    fun getErroneousResultValue(): Throwable {
        return getErroneousResult().exception
    }


    fun isCacheResultSuccessful(): Boolean {
        return (cacheResult is Result.Success)
    }


    fun isCacheResultErroneous(checkNullability: Boolean = false): Boolean {
        return ((checkNullability && (cacheResult == null)) || (cacheResult is Result.Failure))
    }


    fun isServerResultSuccessful(): Boolean {
        return (serverResult is Result.Success)
    }


    fun isServerResultErroneous(checkNullability: Boolean = false): Boolean {
        return ((checkNullability && (serverResult == null)) || (serverResult is Result.Failure))
    }


    fun isDatabaseResultSuccessful(): Boolean {
        return (databaseResult is Result.Success)
    }


    fun isDatabaseResultErroneous(checkNullability: Boolean = false): Boolean {
        return ((checkNullability && (databaseResult == null)) || (databaseResult is Result.Failure))
    }


    /**
     * If either cache, server or database result is successful,
     * then this method will return true. Otherwise, return false.
     *
     * @return true if at least one result is successful; false otherwise
     */
    fun isSuccessful(): Boolean {
        return (isCacheResultSuccessful() ||
                isServerResultSuccessful() ||
                isDatabaseResultSuccessful())
    }


    /**
     * If cache, server, and database results are erroneous,
     * then this method will return true. Otherwise, return false.
     *
     * @return true if all results are erroneous; false otherwise
     */
    fun isErroneous(): Boolean {
        return (isCacheResultErroneous(true) &&
                isServerResultErroneous(true) &&
                isDatabaseResultErroneous(true))
    }


}