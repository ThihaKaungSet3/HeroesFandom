package non.shahad.heroesfandom.ui.home.models

data class ParentComicType(val name : String,
                           val publishers : List<Publisher>)

data class Publisher(val imageUrl : String)