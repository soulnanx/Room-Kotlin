package au.com.vector.study.vector.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(indices = [Index("name", unique = true)])
data class Person (
                   @PrimaryKey(autoGenerate = true) val id : Long = 0,
                   val name: String,
                   val description: String,
                   @ColumnInfo(name = "created_at") val createdAt: Date = Date(),
                   val gender: String = "n/a"
)