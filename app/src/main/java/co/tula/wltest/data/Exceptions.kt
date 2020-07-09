package co.tula.wltest.data

import co.tula.wltest.utils.Either
import com.google.gson.annotations.SerializedName

typealias EitherE<R> = Either<DomainException, R>

sealed class DomainException : Exception() {
    data class ApiException(val error: ApiError) : DomainException()
    object CanceledException : DomainException()
    object UnknownException : DomainException()
    object UnauthorizedException : DomainException()
}

data class ApiError(
    @SerializedName("error")
    val error: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("message")
    val message: String?
)