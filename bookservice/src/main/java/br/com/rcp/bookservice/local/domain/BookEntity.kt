package br.com.rcp.bookservice.local.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val remoteId: String,
    val title: String,
    val authors: List<String>,
    val subtitle: String? = null,
    val description: String? = null,
    val thumbnail: String? = null,
    var informationLink: String? = null,
)
