package non.shahad.heroesfandom.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    @SerializedName("uuid")
    val uuid: String,
    @ColumnInfo(name = "title")
    val title : String,
    @SerializedName("img_url")
    @ColumnInfo(name = "img_url")
    val imgUrl : String,
    @SerializedName("link")
    @ColumnInfo(name = "link")
    val postLink : String,
    @ColumnInfo(name = "universe")
    var universe: String?,
    @ColumnInfo(name = "page")
    var page: Int

)