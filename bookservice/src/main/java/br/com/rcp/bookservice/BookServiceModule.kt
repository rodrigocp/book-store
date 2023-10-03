package br.com.rcp.bookservice

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import br.com.rcp.bookservice.interactor.GetBookByRemoteIdInteractorImpl
import br.com.rcp.bookservice.interactor.GetBookPagerInteractorImpl
import br.com.rcp.bookservice.interactor.base.GetBookByRemoteIdInteractor
import br.com.rcp.bookservice.interactor.base.GetBookPagerInteractor
import br.com.rcp.bookservice.local.database.BookDB
import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.bookservice.mapper.BookEntityToBookMapperImpl
import br.com.rcp.bookservice.mapper.BookToBookEntityMapperImpl
import br.com.rcp.bookservice.mapper.RemoteItemToBookMapperImpl
import br.com.rcp.bookservice.mapper.base.BookEntityToBookMapper
import br.com.rcp.bookservice.mapper.base.BookToBookEntityMapper
import br.com.rcp.bookservice.mapper.base.RemoteItemToBookMapper
import br.com.rcp.bookservice.mediator.BookVolumeRemoteMediator
import br.com.rcp.bookservice.remote.RemoteBookVolumeAPI
import br.com.rcp.bookservice.repository.BookVolumeRepositoryImpl
import br.com.rcp.bookservice.service.RemoteServiceFactoryImpl
import br.com.rcp.bookserviceabstract.repository.BookVolumeRepository
import br.com.rcp.networkabstract.service.ServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface BookServiceModule {
    @Binds
    @Singleton
    fun bindBookVolumeRepository(
        bind: BookVolumeRepositoryImpl
    ): BookVolumeRepository

    @Binds
    @Singleton
    fun bindItemMapper(
        bind: RemoteItemToBookMapperImpl
    ): RemoteItemToBookMapper

    @Binds
    @Singleton
    fun bindVolumeMapper(
        bind: BookToBookEntityMapperImpl
    ): BookToBookEntityMapper

    @Binds
    @Singleton
    fun bindVolumeEntityMapper(
        bind: BookEntityToBookMapperImpl
    ): BookEntityToBookMapper

    @Binds
    @Reusable
    fun bindGetPaginatedBooksInteractor(
        bind: GetBookPagerInteractorImpl
    ): GetBookPagerInteractor

    @Binds
    @Reusable
    fun bindGetBookByRemoteIdInteractor(
        bind: GetBookByRemoteIdInteractorImpl
    ): GetBookByRemoteIdInteractor

    @ExperimentalPagingApi
    companion object {
        @Provides
        fun provideRemoteAPI(
            factory: ServiceFactory
        ): RemoteBookVolumeAPI {
            return RemoteServiceFactoryImpl(factory).create()
        }

        @Provides
        fun provideVolumeDB(
            @ApplicationContext context: Context
        ): BookDB {
            return Room.databaseBuilder(context, BookDB::class.java, "books.db")
                .fallbackToDestructiveMigration() // We do not care about previous data
                .build()
        }
    }
}
