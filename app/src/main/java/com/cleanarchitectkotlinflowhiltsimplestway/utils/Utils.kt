package com.cleanarchitectkotlinflowhiltsimplestway.utils

import com.cleanarchitectkotlinflowhiltsimplestway.presentation.AuthenticationException
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.NetworkErrorException
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Utils {
    companion object{
        fun resolveError(e: Exception): State.ErrorState {

            val error = when (e) {
                is SocketTimeoutException -> {
                    NetworkErrorException(errorMessage = "connection error!")
                }
                is ConnectException -> {
                    NetworkErrorException(errorMessage = "no internet access!")
                }
                is UnknownHostException -> {
                    NetworkErrorException(errorMessage = "no internet access!")
                }
                is HttpException -> {
                    when (e.code()) {
                        502 -> NetworkErrorException(e.code(),  "internal error!")
                        401 -> throw AuthenticationException("authentication error!")
                        400 -> NetworkErrorException.parseException(e)
                        else -> e
                    }
                }
                else -> e
            }
            return State.ErrorState(error)
        }
    }
}