package br.com.rcp.bookservice.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import br.com.rcp.bookservice.local.domain.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {
    @Upsert
    suspend fun upsert(beers: List<BookEntity>)

    @Query("SELECT * FROM BookEntity")
    fun pagingSource(): PagingSource<Int, BookEntity>

    @Query("DELETE FROM BookEntity")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM BookEntity")
    suspend fun getCount(): Long

    @Query("SELECT * FROM BookEntity WHERE remoteId = :id")
    fun findByRemoteID(id: String): Flow<BookEntity>
}
