package br.com.rcp.bookserviceabstract.domain

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val subtitle: String? = null,
    val description: String? = null,
    val thumbnail: String? = null,
    var informationLink: String? = null,
)
