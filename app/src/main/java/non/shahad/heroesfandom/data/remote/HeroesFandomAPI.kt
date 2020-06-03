package non.shahad.heroesfandom.data.remote

import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import non.shahad.heroesfandom.data.remote.responses.HomeDataResponse
import non.shahad.heroesfandom.data.remote.responses.MovieNewsResponse
import non.shahad.heroesfandom.data.remote.responses.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesFandomAPI {

    @GET("news")
    suspend fun getNews() : NewsResponse

    @GET("comic/universe/{name}/{page}")
    suspend fun getComicByUniverse(
        @Path("name")name: String,
        @Path("page")page: Int) : ComicResponse

    @GET("movie/news")
    suspend fun getMovieNews() : MovieNewsResponse

    @GET("home/{page}")
    suspend fun getHomeData(
        @Path("page")page: Int
    ) : HomeDataResponse
}