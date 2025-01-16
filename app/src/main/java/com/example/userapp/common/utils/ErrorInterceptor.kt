package com.example.userapp.common.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import org.json.JSONObject
import java.net.SocketTimeoutException

class ErrorInterceptor : Interceptor {

    sealed class NetworkException(val code: Int, message: String) : IOException(message) {
        class UnauthorizedException(code: Int, message: String) : NetworkException(code, message)
        class ServerErrorException(code: Int, message: String) : NetworkException(code, message)
        class EmptyResponseException(code: Int, message: String) : NetworkException(code, message)
        class UnknownException(code: Int, message: String) : NetworkException(code, message)
        class TimeoutException(message: String) : NetworkException(-1, message)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("Request", "--> ${request.method} ${request.url}")
        try {
            val response = chain.proceed(request)
            Log.d("Response", "<-- ${response.code} ${response.message} ${response.request.url}")
            if (response.isSuccessful) {
                return response
            } else {
                val errorBody = response.body?.string()
                val errorMessage = getErrorMessage(errorBody, response.message)
                when (response.code) {
                    401 -> throw NetworkException.UnauthorizedException(response.code, errorMessage)
                    in 500..599 -> throw NetworkException.ServerErrorException(
                        response.code,
                        errorMessage
                    )

                    else -> throw NetworkException.UnknownException(response.code, errorMessage)
                }
            }
        } catch (e: SocketTimeoutException) {
            e.message?.let { Log.e("Timeout Exception", it) }
            throw NetworkException.TimeoutException("Request timed out")
        } catch (e: Exception) {
            e.message?.let { Log.e("Unknown Exception", it) }
            throw NetworkException.UnknownException(-1, e.message ?: "Unknown error")
        }
    }

    private fun getErrorMessage(errorBody: String?, defaultMessage: String): String {
        return try {
            if (!errorBody.isNullOrEmpty()) {
                val jsonObject = JSONObject(errorBody)
                jsonObject.optString("message", defaultMessage)
            } else {
                defaultMessage
            }
        } catch (e: Exception) {
            defaultMessage
        }
    }
}