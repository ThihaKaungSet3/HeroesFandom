package non.shahad.heroesfandom.core

class Constants {
    object NetworkService {
        const val SUPERHEROAPI_URL = "https://cdn.rawgit.com/"
        const val MOVIESAPI_URL = "https://api.themoviedb.org/3/"
        const val GETCOMIC_URL = "https://getcomics.info/"
        const val RATE_LIMITER_TYPE = "data"
        const val API_KEY = "b5817937e7b7451f9ba847baac9c781c"
        const val GENRE = 878
    }

    object Persistence{
        const val DATABASE_NAME = "heroes-db"
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