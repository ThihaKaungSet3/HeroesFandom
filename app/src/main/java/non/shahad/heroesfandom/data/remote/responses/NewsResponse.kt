package non.shahad.heroesfandom.data.remote.responses

import com.google.gson.annotations.SerializedName
import non.shahad.heroesfandom.data.db.entities.NewsEntity

data class NewsResponse(
    @SerializedName("latest_news")val latestNews : List<NewsEntity>
)