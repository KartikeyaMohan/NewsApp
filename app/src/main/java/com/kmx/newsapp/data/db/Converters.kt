package com.kmx.newsapp.data.db

import androidx.room.TypeConverter
import com.kmx.newsapp.data.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}