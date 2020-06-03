package non.shahad.heroesfandom.data.remote.responses

import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity

data class MovieNewsResponse(
    val news: List<MovieNewsEntity>
)