package br.com.rcp.bookservice.interactor.base

import androidx.paging.Pager
import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.core.models.Interactor

interface GetBookPagerInteractor : Interactor<String, Pager<Int, BookEntity>>
