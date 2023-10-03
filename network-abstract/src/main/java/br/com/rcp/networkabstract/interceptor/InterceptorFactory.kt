package br.com.rcp.networkabstract.interceptor

import okhttp3.Interceptor

interface InterceptorFactory {
    fun create(): Interceptor
}
