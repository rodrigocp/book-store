package br.com.rcp.bookserviceabstract.service

import br.com.rcp.bookserviceabstract.remote.RemoteAPI

interface RemoteServiceFactory<T: RemoteAPI> {
    fun create(): T
}
