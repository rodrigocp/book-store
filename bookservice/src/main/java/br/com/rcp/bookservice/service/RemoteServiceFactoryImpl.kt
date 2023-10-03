package br.com.rcp.bookservice.service

import br.com.rcp.bookservice.remote.RemoteBookVolumeAPI
import br.com.rcp.bookserviceabstract.service.RemoteServiceFactory
import br.com.rcp.networkabstract.service.ServiceFactory
import javax.inject.Inject

internal class RemoteServiceFactoryImpl @Inject constructor(
    private val service: ServiceFactory
) : RemoteServiceFactory<RemoteBookVolumeAPI> {

    override fun create(): RemoteBookVolumeAPI {
        return service.create(RemoteBookVolumeAPI::class.java)
    }
}
