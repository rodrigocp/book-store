package br.com.rcp.bookservice.remote

import br.com.rcp.bookservice.dto.BooksDTO
import br.com.rcp.bookservice.dto.ItemDTO
import br.com.rcp.bookserviceabstract.remote.RemoteAPI
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RemoteBookVolumeAPI : RemoteAPI {
    @GET(".")
    suspend fun queryVolumesByContaining(
        @Query("q") query: String,
        @Query("startIndex") start: Int,
        @Query("maxResults") limit: Int,
        @Query("projection") projection: String = "lite"
    ): BooksDTO
}
