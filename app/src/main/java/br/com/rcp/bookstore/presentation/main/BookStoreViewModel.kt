package br.com.rcp.bookstore.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.rcp.bookservice.interactor.base.GetBookByRemoteIdInteractor
import br.com.rcp.bookservice.interactor.base.GetBookPagerInteractor
import br.com.rcp.bookservice.mapper.base.BookEntityToBookMapper
import br.com.rcp.bookserviceabstract.domain.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BookStoreViewModel @Inject constructor(
    private val getBookPagerInteractor: GetBookPagerInteractor,
    private val getBookByRemoteIdInteractor: GetBookByRemoteIdInteractor,
    private val bookEntityToBookMapper: BookEntityToBookMapper
) : ViewModel() {
    val bookPagingFlow: Flow<PagingData<Book>> = getBookPagerInteractor.execute("ios").flow.map { data ->
        data.map {
            bookEntityToBookMapper.map(it)
        }
    }.cachedIn(viewModelScope)

    fun getBookByRemoteId(id: String): Flow<Book> {
        return getBookByRemoteIdInteractor.execute(id)
    }

}
