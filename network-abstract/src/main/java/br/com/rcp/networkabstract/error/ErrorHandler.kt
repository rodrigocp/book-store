package br.com.rcp.networkabstract.error

import br.com.rcp.core.models.RemoteError
import retrofit2.HttpException

interface ErrorHandler {
    fun handle(exception: HttpException): RemoteError
}
