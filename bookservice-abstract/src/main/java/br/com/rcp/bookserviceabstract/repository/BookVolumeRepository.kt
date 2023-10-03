package br.com.rcp.bookserviceabstract.repository

import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.core.models.RemoteWrapper
import kotlinx.coroutines.flow.Flow

interface BookVolumeRepository {
    suspend fun fetchAllVolumesContaining(value: String, start: Int, limit: Int): RemoteWrapper<List<Book>>
    fun fetchOneByRemoteID(value: String): Flow<Book>
}
