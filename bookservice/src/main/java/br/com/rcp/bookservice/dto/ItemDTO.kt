package br.com.rcp.bookservice.dto

import com.google.gson.annotations.SerializedName

internal data class ItemDTO(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volume: VolumeDTO
)
