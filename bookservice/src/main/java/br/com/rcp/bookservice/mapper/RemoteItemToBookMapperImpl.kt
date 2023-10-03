package br.com.rcp.bookservice.mapper

import android.content.Context
import br.com.rcp.bookservice.R
import br.com.rcp.bookservice.dto.ItemDTO
import br.com.rcp.bookservice.mapper.base.RemoteItemToBookMapper
import br.com.rcp.bookserviceabstract.domain.Book
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class RemoteItemToBookMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
): RemoteItemToBookMapper {
    override fun map(data: ItemDTO): Book {
        return Book(
            id = data.id,
            title = data.volume.title ?: context.resources.getString(R.string.volume_no_title),
            authors = data.volume.authors,
            subtitle = data.volume.subtitle,
            description = data.volume.description,
            thumbnail = data.volume.thumbnails?.large,
            informationLink = data.volume.link
        )
    }
}
