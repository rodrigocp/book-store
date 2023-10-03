package br.com.rcp.bookservice.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.rcp.bookservice.local.converters.StringListConverter
import br.com.rcp.bookservice.local.dao.BookDAO
import br.com.rcp.bookservice.local.domain.BookEntity

@Database(entities = [BookEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
internal abstract class BookDB : RoomDatabase() {
    abstract val bookDAO: BookDAO
}
