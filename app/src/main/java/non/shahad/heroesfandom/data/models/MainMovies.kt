package non.shahad.heroesfandom.data.models

import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.db.entities.MovieEntity
import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity

/**
 * id if for work around
 */
data class MainMovies (val id : Int,
                       val viewTypes: ViewTypes,
                       val movieList : List<MovieEntity>? = null,
                       val bannerItemList : List<Banner>? = null,
                       val newsItemList: List<MovieNewsEntity>? = null,
                       val title : String = ""
)

data class Banner(val posterUrl : String,
                  val title : String)