package non.shahad.heroesfandom.data.remote.responses

import non.shahad.heroesfandom.data.local.entities.ComicEntity

/**
 * Work around
 */
data class ComicResponse(
    val categoryName : String,
    val page : Int,
    val list: ArrayList<ComicEntity>
) {
}