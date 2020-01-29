package non.shahad.heroesfandom.ui.movies.models

import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.local.entities.MovieEntity

data class MainMovies (val viewTypes: ViewTypes,
                       val movieList : List<MovieEntity>?,
                       val bannerItemList : List<Banner>?,
                       val title : String = ""
)

data class Banner(val posterUrl : String,
                  val title : String)