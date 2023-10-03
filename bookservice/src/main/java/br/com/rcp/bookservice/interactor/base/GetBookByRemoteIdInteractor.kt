package br.com.rcp.bookservice.interactor.base

import androidx.paging.Pager
import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.core.models.Interactor
import kotlinx.coroutines.flow.Flow

interface GetBookByRemoteIdInteractor : Interactor<String, Flow<Book>>
