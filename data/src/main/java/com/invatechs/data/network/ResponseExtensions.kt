package com.invatechs.data.network

import retrofit2.Response
import com.invatechs.domain.utils.Result

fun <T> Response<T>.bodyOrFailure(): Result<T> {
    if (isSuccessful) {
        body()?.let {
            return Result.Success(it)
        }
    }
    return Result.Failure(errorBody())
}