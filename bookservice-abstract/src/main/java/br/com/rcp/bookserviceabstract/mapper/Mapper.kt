package br.com.rcp.bookserviceabstract.mapper

interface Mapper<I, O> {
    fun map(data: I): O
}
