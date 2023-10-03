package br.com.rcp.network.api

import br.com.rcp.network.BuildConfig
import br.com.rcp.networkabstract.api.RemoteAPI
import javax.inject.Inject

internal class RemoteApiImpl @Inject constructor() : RemoteAPI{
    override val baseURL: String
        get() = BuildConfig.BASE_URL
}
