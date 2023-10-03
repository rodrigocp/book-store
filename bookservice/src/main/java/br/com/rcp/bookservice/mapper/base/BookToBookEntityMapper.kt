package br.com.rcp.bookservice.mapper.base

import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookserviceabstract.mapper.Mapper

interface BookToBookEntityMapper : Mapper<Book, BookEntity>
