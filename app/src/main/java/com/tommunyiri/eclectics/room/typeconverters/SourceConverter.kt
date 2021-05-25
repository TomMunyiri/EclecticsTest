package com.tommunyiri.eclectics.room.typeconverters;

import androidx.room.TypeConverter;
import com.squareup.moshi.Moshi

import com.tommunyiri.eclectics.models.Source;

class SourceConverter {
    @TypeConverter
    fun sourceToString(input: Source): String? =
        Moshi.Builder().build().adapter(Source::class.java).toJson(input)
    @TypeConverter
    fun stringToSource(input: String?): Source? =
        input?.let { Moshi.Builder().build().adapter(Source::class.java).fromJson(it) }
}
