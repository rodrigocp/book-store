package br.com.rcp.networkabstract.service

interface ServiceFactory {
    fun <T> create(service: Class<T>): T
}
