package br.com.rcp.network.interceptor

import br.com.rcp.network.BuildConfig
import br.com.rcp.networkabstract.interceptor.InterceptorFactory
import javax.inject.Inject
import okhttp3.Interceptor

internal class HeaderInterceptorFactoryImpl @Inject constructor() : InterceptorFactory {
    override fun create(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header(BuildConfig.API_KEY_HEADER, BuildConfig.API_KEY_VALUE)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }
}
