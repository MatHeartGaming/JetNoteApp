package it.spaarkly.jetnoteapp.model

import androidx.room.*
import java.time.Instant
import java.util.*

@Entity(tableName = "notes_tbl")
data class Note(

    @PrimaryKey
    val id : UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    val title : String,

    @ColumnInfo(name = "note_description")
    val description : String,

    @ColumnInfo(name = "note_entry")
    @field:TypeConverters(DateConverter::class)
    val entryDate : Date = Date.from(Instant.now())
)

object DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}