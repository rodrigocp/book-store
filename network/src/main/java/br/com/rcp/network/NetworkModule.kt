package br.com.rcp.network

import br.com.rcp.commons.annotations.CachingInterceptor
import br.com.rcp.commons.annotations.HeadersInterceptor
import br.com.rcp.commons.annotations.LoggingInterceptor
import br.com.rcp.network.api.RemoteApiImpl
import br.com.rcp.network.cache.CacheFactoryImpl
import br.com.rcp.network.client.HttpClientFactoryImpl
import br.com.rcp.network.error.ErrorHandlerImpl
import br.com.rcp.network.interceptor.CachingInterceptorFactoryImpl
import br.com.rcp.network.interceptor.HeaderInterceptorFactoryImpl
import br.com.rcp.network.interceptor.LoggingInterceptorFactoryImpl
import br.com.rcp.network.request.RequestHandlerImpl
import br.com.rcp.network.service.ServiceFactoryImpl
import br.com.rcp.networkabstract.api.RemoteAPI
import br.com.rcp.networkabstract.cache.CacheFactory
import br.com.rcp.networkabstract.client.HttpClientFactory
import br.com.rcp.networkabstract.error.ErrorHandler
import br.com.rcp.networkabstract.interceptor.InterceptorFactory
import br.com.rcp.networkabstract.request.RequestHandler
import br.com.rcp.networkabstract.service.ServiceFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {
    @Binds
    @LoggingInterceptor
    fun bindLoggingInterceptorFactory(
        bind: LoggingInterceptorFactoryImpl
    ): InterceptorFactory

    @Binds
    @CachingInterceptor
    fun bindCachingInterceptorFactory(
        bind: CachingInterceptorFactoryImpl
    ): InterceptorFactory

    @Binds
    @HeadersInterceptor
    fun bindHeadersInterceptorFactory(
        bind: HeaderInterceptorFactoryImpl
    ): InterceptorFactory

    @Binds
    @Singleton
    fun bindErrorHandler(
        bind: ErrorHandlerImpl
    ): ErrorHandler

    @Binds
    @Singleton
    fun bindRequestHandler(
        bind: RequestHandlerImpl
    ): RequestHandler

    @Binds
    fun bindAPI(
        bind: RemoteApiImpl
    ): RemoteAPI

    @Binds
    fun bindCacheFactory(
        bind: CacheFactoryImpl
    ): CacheFactory

    @Binds
    fun bindServiceFactory(
        bind: ServiceFactoryImpl
    ): ServiceFactory

    @Binds
    fun bindHttpClientFactory(
        bind: HttpClientFactoryImpl
    ): HttpClientFactory
}
