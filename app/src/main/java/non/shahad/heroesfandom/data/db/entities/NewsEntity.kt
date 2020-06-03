package non.shahad.heroesfandom.data.db.entities

import com.google.gson.annotations.SerializedName


data class NewsEntity(
    @SerializedName("img_url")
    val imgUrl : String,
    @SerializedName("title")
    val title: String,
    @SerializedName("date")
    val date: String
)