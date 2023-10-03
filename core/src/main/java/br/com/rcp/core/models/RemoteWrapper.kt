package br.com.rcp.core.models

sealed class RemoteWrapper<out T> {
    data class Success<T>(val response: T) : RemoteWrapper<T>()
    data class Failure<T>(val detailed: RemoteError) : RemoteWrapper<T>()
}

fun <T> RemoteWrapper<T>.onFailure(invokable: (RemoteError) -> Unit): RemoteWrapper<T> {
    if (this is RemoteWrapper.Failure) invokable.invoke(this.detailed)
    return this
}

fun <T> RemoteWrapper<T>.onSuccess(invokable: (T) -> Unit): RemoteWrapper<T> {
    if (this is RemoteWrapper.Success) invokable.invoke(this.response)
    return this
}
