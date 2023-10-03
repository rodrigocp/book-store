package br.com.rcp.bookservice.mapper

import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.bookservice.mapper.base.BookEntityToBookMapper
import br.com.rcp.bookserviceabstract.domain.Book
import javax.inject.Inject

internal class BookEntityToBookMapperImpl @Inject constructor(): BookEntityToBookMapper {
    override fun map(data: BookEntity): Book {
        return Book(
            id = data.remoteId,
            title = data.title,
            authors = data.authors,
            subtitle = data.subtitle,
            description = data.description,
            thumbnail = data.thumbnail,
            informationLink = data.informationLink
        )
    }
}
