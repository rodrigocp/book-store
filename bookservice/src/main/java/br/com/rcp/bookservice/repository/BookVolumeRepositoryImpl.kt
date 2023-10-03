package br.com.rcp.bookservice.repository

import br.com.rcp.bookservice.local.dao.BookDAO
import br.com.rcp.bookservice.local.database.BookDB
import br.com.rcp.bookservice.mapper.base.BookEntityToBookMapper
import br.com.rcp.bookservice.mapper.base.RemoteItemToBookMapper
import br.com.rcp.bookservice.remote.RemoteBookVolumeAPI
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookserviceabstract.repository.BookVolumeRepository
import br.com.rcp.core.models.RemoteWrapper
import br.com.rcp.networkabstract.request.RequestHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BookVolumeRepositoryImpl @Inject constructor(
    private val database: BookDB,
    private val handler: RequestHandler,
    private val remote: RemoteBookVolumeAPI,
    private val localMapper: BookEntityToBookMapper,
    private val remoteMapper: RemoteItemToBookMapper,
) : BookVolumeRepository {
    override suspend fun fetchAllVolumesContaining(value: String, start: Int, limit: Int): RemoteWrapper<List<Book>> {
        return handler {
            remote.queryVolumesByContaining(value, start, limit).let { data ->
                data.items.map {
                    remoteMapper.map(it)
                }
            }
        }
    }

    override fun fetchOneByRemoteID(value: String): Flow<Book> {
        return database.bookDAO.findByRemoteID(value).map { localMapper.map(it) }
    }
}
