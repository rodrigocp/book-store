package br.com.rcp.network.interceptor

import br.com.rcp.networkabstract.interceptor.InterceptorFactory
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptorFactoryImpl @Inject constructor() : InterceptorFactory {
    override fun create(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
