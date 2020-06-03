package non.shahad.heroesfandom.data.remote.responses

import com.google.gson.annotations.SerializedName
import non.shahad.heroesfandom.data.db.entities.ComicEntity
import non.shahad.heroesfandom.data.db.entities.GamingNewsEntitiy
import non.shahad.heroesfandom.data.db.entities.NewsEntity
import non.shahad.heroesfandom.data.db.entities.ScienceNews

data class HomeDataResponse (
    val last: Boolean,
    @SerializedName("heroes_news")
    val heroesNews : List<NewsEntity>?,
    @SerializedName("marvel_comic")
    val marvelComics: List<ComicEntity>?,
    @SerializedName("dc_comic")
    val dcComics: List<ComicEntity>?,
    @SerializedName("science_news")
    val scienceNews : List<ScienceNews>?,
    @SerializedName("gaming_news")
    val gamingNews: List<GamingNewsEntitiy>?
)