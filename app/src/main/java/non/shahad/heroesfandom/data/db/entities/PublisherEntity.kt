package non.shahad.heroesfandom.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publisher")
data class PublisherEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")val name : String = "",
    @ColumnInfo(name = "poster_url")val posterUrl : String)