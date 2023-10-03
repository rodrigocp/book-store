package br.com.rcp.network.interceptor

import br.com.rcp.networkabstract.interceptor.InterceptorFactory
import javax.inject.Inject
import okhttp3.Interceptor

internal class CachingInterceptorFactoryImpl @Inject constructor() : InterceptorFactory {
    override fun create(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val maxAge = 60
            return@Interceptor response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
    }
}
