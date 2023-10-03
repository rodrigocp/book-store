package br.com.rcp.bookservice.interactor

import br.com.rcp.bookservice.interactor.base.GetBookByRemoteIdInteractor
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookserviceabstract.repository.BookVolumeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class GetBookByRemoteIdInteractorImpl @Inject constructor(
    private val repository: BookVolumeRepository
): GetBookByRemoteIdInteractor {
    override fun execute(value: String): Flow<Book> {
        return repository.fetchOneByRemoteID(value)
    }
}
