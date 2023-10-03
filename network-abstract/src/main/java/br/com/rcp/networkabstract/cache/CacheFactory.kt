package br.com.rcp.networkabstract.cache

import okhttp3.Cache

interface CacheFactory {
    fun create(): Cache
}

