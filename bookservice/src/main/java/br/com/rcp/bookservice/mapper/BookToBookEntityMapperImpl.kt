package br.com.rcp.bookservice.mapper

import br.com.rcp.bookservice.local.domain.BookEntity
import br.com.rcp.bookservice.mapper.base.BookToBookEntityMapper
import br.com.rcp.bookserviceabstract.domain.Book
import javax.inject.Inject

internal class BookToBookEntityMapperImpl @Inject constructor(): BookToBookEntityMapper {
    override fun map(data: Book): BookEntity {
        return BookEntity(
            remoteId = data.id,
            title = data.title,
            authors = data.authors,
            subtitle = data.subtitle,
            description = data.description,
            thumbnail = data.thumbnail,
            informationLink = data.informationLink
        )
    }
}
