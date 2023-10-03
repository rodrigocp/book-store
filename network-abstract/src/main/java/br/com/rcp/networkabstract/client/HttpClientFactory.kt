package br.com.rcp.networkabstract.client

import okhttp3.OkHttpClient

interface HttpClientFactory {
    fun create(): OkHttpClient
}
