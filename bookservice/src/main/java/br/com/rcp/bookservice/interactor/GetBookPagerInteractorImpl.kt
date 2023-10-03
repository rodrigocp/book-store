package br.com.rcp.bookservice.interactor

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.rcp.bookservice.interactor.base.GetBookPagerInteractor
import br.com.rcp.bookservice.local.database.BookDB
import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.bookservice.mapper.base.BookToBookEntityMapper
import br.com.rcp.bookservice.mediator.BookVolumeRemoteMediator
import br.com.rcp.bookserviceabstract.repository.BookVolumeRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class GetBookPagerInteractorImpl @Inject constructor(
    private val database: BookDB,
    private val repository: BookVolumeRepository,
    private val mapper: BookToBookEntityMapper
) : GetBookPagerInteractor {
    override fun execute(value: String): Pager<Int, BookEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BookVolumeRemoteMediator(database, repository, mapper, value),
            pagingSourceFactory = { database.bookDAO.pagingSource() }
        )
    }
}
