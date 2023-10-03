package br.com.rcp.network.client

import br.com.rcp.commons.annotations.CachingInterceptor
import br.com.rcp.commons.annotations.HeadersInterceptor
import br.com.rcp.commons.annotations.LoggingInterceptor
import br.com.rcp.networkabstract.cache.CacheFactory
import br.com.rcp.networkabstract.client.HttpClientFactory
import br.com.rcp.networkabstract.interceptor.InterceptorFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class HttpClientFactoryImpl @Inject constructor(
    private val cache: CacheFactory,
    @LoggingInterceptor private val loggingInterceptor: InterceptorFactory,
    @CachingInterceptor private val cachingInterceptor: InterceptorFactory,
    @HeadersInterceptor private val headersInterceptor: InterceptorFactory
) : HttpClientFactory {
    override fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor.create())
            .addInterceptor(headersInterceptor.create())
            .addNetworkInterceptor(cachingInterceptor.create())
            .cache(cache.create())
            .build()
    }
}
