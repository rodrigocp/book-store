package br.com.rcp.network.cache

import android.content.Context
import br.com.rcp.network.BuildConfig
import br.com.rcp.networkabstract.cache.CacheFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import javax.inject.Inject

internal class CacheFactoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CacheFactory {
    override fun create(): Cache {
        return Cache(context.cacheDir, BuildConfig.CACHE_SIZE)
    }
}
