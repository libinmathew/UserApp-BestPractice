package com.example.userapp.data


sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Failure(val error: AppError) : DataState<Nothing>()
}

sealed interface AppError {
    val message: String
    val context: Map<String, Any?>?

    sealed class NetworkError(
        override val message: String,
        open val code: Int,
        override val context: Map<String, Any?>? = null
    ) : AppError {
        data class UnauthorizedError(
            override val message: String,
            override val context: Map<String, Any?>? = null,
            override val code: Int = 401
        ) : NetworkError(message, code, context)

        data class ServerError(
            override val message: String,
            override val context: Map<String, Any?>? = null,
            override val code: Int
        ) : NetworkError(message, code, context)

        data class UnknownError(
            override val message: String,
            override val context: Map<String, Any?>? = null,
            override val code: Int = -1
        ) : NetworkError(message, code, context)

        data class TimeoutError(
            override val message: String,
            override val context: Map<String, Any?>? = null
        ) : NetworkError(message, -1, context)
    }

    data class UnknownAppError(
        override val message: String = "Unknown error",
        override val context: Map<String, Any?>? = null
    ) : AppError

    data class CustomAppError(
        override val message: String,
        override val context: Map<String, Any?>? = null
    ) : AppError
}


