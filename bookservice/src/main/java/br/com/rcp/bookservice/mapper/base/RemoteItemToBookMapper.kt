package br.com.rcp.bookservice.mapper.base

import br.com.rcp.bookservice.dto.ItemDTO
import br.com.rcp.bookserviceabstract.domain.Book
import br.com.rcp.bookserviceabstract.mapper.Mapper

internal interface RemoteItemToBookMapper : Mapper<ItemDTO, Book>
