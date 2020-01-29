package non.shahad.heroesfandom.data.remote.request

data class ComicRequest(
    val universe : String,
    val page : Int
)

data class ComicByTagRequest(
    val name : String,
    val page : Int
)