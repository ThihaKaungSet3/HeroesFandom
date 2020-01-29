package non.shahad.heroesfandom.ui.home.models

import non.shahad.heroesfandom.domain.model.Comic
import non.shahad.heroesfandom.domain.model.Publisher
import non.shahad.heroesfandom.core.ViewTypes

data class Home(val title : String,
                val viewTypes : ViewTypes,
                val parentComic: ParentComic?,
                val parentPublisher : ParentPublisher?)

data class ParentComic(var name : String? = "Latest Release",
                       val list: List<Comic>)

data class ParentPublisher(var name: String? = "Publishers",
                           val list: List<Publisher>){

}
