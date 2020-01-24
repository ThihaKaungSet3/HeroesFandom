package non.shahad.heroesfandom.domain.model

data class Comic(
    val title : String,
    val posterUrl : String,
    val category : String,
    val postLink : String,
    val publishedDate : String,
    val releaseDate : String = ""
) {
}