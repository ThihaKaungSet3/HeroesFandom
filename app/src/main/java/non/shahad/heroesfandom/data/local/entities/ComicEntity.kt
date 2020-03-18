package non.shahad.heroesfandom.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "poster_url")
    val posterUrl : String,
    @ColumnInfo(name = "category")
    var category : String,
    @ColumnInfo(name = "post_link")
    val postLink : String,
    @ColumnInfo(name = "published_date")
    val publishedDate : String,
    @ColumnInfo(name = "release_date")
    val releaseDate : String = "",
    @ColumnInfo(name = "page")
    var page : Int = -1
)