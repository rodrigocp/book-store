package br.com.rcp.commons

import br.com.rcp.commons.providers.StringProvider
import br.com.rcp.commons.providers.StringproviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CommonsModule {
    @Binds
    @Singleton
    abstract fun bindStringProvider(
        stringproviderImpl: StringproviderImpl
    ) : StringProvider
}
