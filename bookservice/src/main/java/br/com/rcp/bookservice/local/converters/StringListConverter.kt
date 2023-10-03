package br.com.rcp.bookservice.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }
}
