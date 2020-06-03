package non.shahad.heroesfandom.data.remote.responses

import com.google.gson.annotations.SerializedName
import non.shahad.heroesfandom.data.db.entities.ComicEntity

/**
 * Work around
 */
data class ComicResponse(
    @SerializedName("category_name")
    val categoryName : String,
    @SerializedName("total_page")
    val totalPage : Int,
    @SerializedName("posts")
    val posts: List<ComicEntity>
) {
}