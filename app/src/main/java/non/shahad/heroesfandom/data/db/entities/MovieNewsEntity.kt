package non.shahad.heroesfandom.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * page is just a key and it's not a big deal
 */
@Entity(tableName = "movie_news")
data class MovieNewsEntity(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "img_url")
    @SerializedName("img_url")
    val imgUrl: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "page")
    var page: Int
)