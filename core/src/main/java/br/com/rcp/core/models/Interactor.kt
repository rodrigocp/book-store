package br.com.rcp.core.models

interface Interactor<I, O> {
    fun execute(value: I): O
}
