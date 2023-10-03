package br.com.rcp.commons.providers

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class StringproviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StringProvider {
    override fun getStringResource(resource: Int): String = context.getString(resource)
}
