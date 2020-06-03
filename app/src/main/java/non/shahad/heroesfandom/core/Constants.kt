package non.shahad.heroesfandom.core

object Constants {
    object NetworkService {
        const val FANDOMURL = "https://heroesfandom.herokuapp.com/"
        const val SUPERHEROAPI_URL = "https://cdn.rawgit.com/"
        const val MOVIESAPI_URL = "https://api.themoviedb.org/3/"
        const val GETCOMIC_URL = "https://getcomics.info/"
        const val PUBLISHER_API = "https://raw.githubusercontent.com/ThihaKaungSet3/heroesfandom.github.io/master/"
        const val RATE_LIMITER_TYPE = "data"
        const val API_KEY = "b5817937e7b7451f9ba847baac9c781c"
        const val GENRE = 878
        const val DISCOVER = "discover"
        const val TRENDING = "trending"
    }

    object Persistence{
        const val DATABASE_NAME = "heroes-db"
        const val APP_PREF_NAME = "heroes-pref"
    }

    object BottomNav {
        const val INDEX_HOME = 0
        const val INDEX_MOVIES = 1
        const val INDEX_HEROES = 2
        const val INDEX_LIBRARY = 3
    }

    object Adapter{
        const val COMIC_VIEWTYPE = 1
        const val PUBLISHER_VIEWTYPE = 2
        const val BANNER_VIEWTYPE = 1
        const val MOVIE_VIEWTYPE = 2
    }

}