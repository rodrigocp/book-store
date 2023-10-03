package br.com.rcp.bookservice.dto

import com.google.gson.annotations.SerializedName

internal data class ImagesDTO(
    @SerializedName("smallThumbnail") val small: String? = null,
    @SerializedName("thumbnail") val large: String? = null
)
