package com.test.rssfeedreader.api.utils

import com.test.rssfeedreader.api.exceptions.ApiException
import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.utils.model.Result
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

class ResponseExtractor() {


    private fun <T> toResult(call: Call<T>): Result<T> {
        return try {
            val response = call.execute()

            if(response.isSuccessful) {
                val body = response.body()

                if(body != null) {
                    Result.Success(body)
                } else {
                    Result.Failure(NullPointerException("Response body is null."))
                }
            } else {
                Result.Failure(HttpException(response))
            }
        } catch(exception: Exception) {
            Result.Failure(exception)
        }
    }


    private fun <In, Out> handleErrorResponse(
        response: Response<In>
    ): Result<Out> {
        if(response.errorBody() !is ResponseBody) {
            return Result.Failure(ApiException())
        }

        return Result.Failure(ApiException())
    }


    private fun <In, Out> extractResult(
        call: Call<In>,
        onSuccess: ((In) -> Result<Out>)
    ): Result<Out> {
        val result = toResult(call)

        return when(result) {
            is Result.Success -> onSuccess(result.value)
            is Result.Failure -> if(result.exception is HttpException) {
                handleErrorResponse(
                    response = (result.exception as HttpException).response()
                )
            } else {
                result
            }
        }
    }


    private fun <T> extractSimpleResult(
        call: Call<T>,
        onSuccess: ((T) -> Result<T>) = { Result.Success(it) }
    ): Result<T> {
        return extractResult(
            call = call,
            onSuccess = onSuccess
        )
    }


    fun extractNewsResponse(call: Call<RssXmlModel>): Result<RssXmlModel> {
        return extractSimpleResult(
            call = call
        )
    }


}