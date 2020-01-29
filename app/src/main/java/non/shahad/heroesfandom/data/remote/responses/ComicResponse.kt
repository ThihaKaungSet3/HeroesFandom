package non.shahad.heroesfandom.data.remote.responses

import non.shahad.heroesfandom.domain.model.Comic

/**
 * Work around
 */
data class ComicResponse(
    val categoryName : String,
    val page : Int,
    val list: ArrayList<Comic>
) {
}