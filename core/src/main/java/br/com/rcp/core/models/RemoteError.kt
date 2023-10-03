package br.com.rcp.core.models

sealed class RemoteError(open val message: String) {
    data class Unauthorized(override val message: String) : RemoteError(message)
    data class BadRequest(override val message: String) : RemoteError(message)
    data class NotFound(override val message: String) : RemoteError(message)
    data class AccessDenied(override val message: String) : RemoteError(message)
    data class ServiceUnavailable(override val message: String) : RemoteError(message)
    data class ServiceInternalError(override val message: String) : RemoteError(message)
    data class Unknown(override val message: String) : RemoteError(message)
    data class Network(override val message: String) : RemoteError(message)
}
