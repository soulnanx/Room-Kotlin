package au.com.vector.study.vector.data

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date?{
        return value?.let { Date(value) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time ?: 0
    }
}