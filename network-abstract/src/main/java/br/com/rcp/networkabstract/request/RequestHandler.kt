package br.com.rcp.networkabstract.request

import br.com.rcp.core.models.RemoteWrapper

interface RequestHandler {
    suspend operator fun <T> invoke(request: suspend() -> (T)): RemoteWrapper<T>
}
