package br.com.rcp.network.service

import br.com.rcp.networkabstract.api.RemoteAPI
import br.com.rcp.networkabstract.client.HttpClientFactory
import br.com.rcp.networkabstract.service.ServiceFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

internal class ServiceFactoryImpl @Inject constructor(
    private val client: HttpClientFactory,
    private val api: RemoteAPI
) : ServiceFactory {
    override fun <T> create(service: Class<T>): T {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(api.baseURL)
            .client(client.create())
            .build()
            .create(service)
    }
}
