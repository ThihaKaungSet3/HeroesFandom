package non.shahad.heroesfandom.data.models

import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.db.entities.*

data class Home(val id : Int,
                val viewTypes : ViewTypes,
                val parentComic: ParentComic? = null,
                val parentPublisher : ParentPublisher? = null,
                val parentNews: ParentNews? = null,
                val parentScienceNews : ParentScienceNews? = null,
                val parentGamingNews: ParentGamingNews? = null,
                val title : String = "")

data class ParentComic(var name : String? = "Latest Release",
                       val list: List<ComicEntity>)

data class ParentPublisher(var name: String? = "Publishers",
                           val list: List<PublisherEntity>)

data class ParentNews(var name: String? = "News",
                      val list: List<NewsEntity>)

data class ParentScienceNews(var name: String? = "Science News",
                             var list: List<ScienceNews>)

data class ParentGamingNews(var name: String? = "Gaming News",
                            var list: List<GamingNewsEntitiy>)

