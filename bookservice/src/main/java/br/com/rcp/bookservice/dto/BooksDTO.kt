package br.com.rcp.bookservice.dto

import com.google.gson.annotations.SerializedName

internal data class BooksDTO(
    @SerializedName("items") var items: List<ItemDTO> = listOf()
)
