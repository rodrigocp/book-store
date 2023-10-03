package br.com.rcp.bookservice.dto

import com.google.gson.annotations.SerializedName

internal data class VolumeDTO(
    @SerializedName("title") var title: String? = null,
    @SerializedName("subtitle") var subtitle: String? = null,
    @SerializedName("authors") var authors: List<String> = listOf(),
    @SerializedName("description") var description: String? = null,
    @SerializedName("imageLinks") var thumbnails: ImagesDTO? = null,
    @SerializedName("infoLink") var link: String? = null,
)
