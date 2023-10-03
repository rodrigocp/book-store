package br.com.rcp.bookservice.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.com.rcp.bookservice.local.database.BookDB
import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.bookservice.mapper.base.BookToBookEntityMapper
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookserviceabstract.repository.BookVolumeRepository
import br.com.rcp.core.models.RemoteWrapper
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class BookVolumeRemoteMediator @Inject constructor(
    private val database: BookDB,
    private val repository: BookVolumeRepository,
    private val mapper: BookToBookEntityMapper,
    private val query: String
) : RemoteMediator<Int, BookEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, BookEntity>): MediatorResult {
        val offset = getStartOffset(loadType, state)

        if (offset < 0) {
            return MediatorResult.Success(endOfPaginationReached = true)
        }

        return repository.fetchAllVolumesContaining(query, offset, state.config.pageSize).let {
            when (it) {
                is RemoteWrapper.Success -> getSuccessMediatorResult(it, loadType, mapper)
                is RemoteWrapper.Failure -> MediatorResult.Error(Exception(it.detailed.message))
            }
        }
    }

    private suspend fun getStartOffset(
        type: LoadType,
        state: PagingState<Int, BookEntity>
    ): Int {
        val last = state.lastItemOrNull()

        return when(type) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> -1
            LoadType.APPEND -> (if (last == null) 0 else database.bookDAO.getCount() + state.config.pageSize).toInt()
        }
    }

    private suspend fun getSuccessMediatorResult(
        response: RemoteWrapper<List<Book>>,
        type: LoadType,
        mapper: BookToBookEntityMapper
    ): MediatorResult {
        return (response as RemoteWrapper.Success).let { wrapper ->
            database.withTransaction {
                if (type == LoadType.REFRESH) {
                    database.bookDAO.clear()
                }
                database.bookDAO.upsert(wrapper.response.map { mapper.map(it) })
            }
            MediatorResult.Success(endOfPaginationReached = wrapper.response.isEmpty())
        }
    }
}
