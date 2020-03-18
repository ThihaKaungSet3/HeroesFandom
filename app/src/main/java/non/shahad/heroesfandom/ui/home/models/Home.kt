package non.shahad.heroesfandom.ui.home.models

import non.shahad.heroesfandom.data.local.entities.ComicEntity
import non.shahad.heroesfandom.data.local.entities.PublisherEntity
import non.shahad.heroesfandom.core.ViewTypes

data class Home(val id : Int,
                val viewTypes : ViewTypes,
                val parentComic: ParentComic?,
                val parentPublisher : ParentPublisher?,
                val title : String = "")

data class ParentComic(var name : String? = "Latest Release",
                       val list: List<ComicEntity>)

data class ParentPublisher(var name: String? = "Publishers",
                           val list: List<PublisherEntity>){

}
