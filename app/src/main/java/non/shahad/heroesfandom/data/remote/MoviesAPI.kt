package non.shahad.heroesfandom.data.remote

import io.reactivex.Single
import non.shahad.heroesfandom.data.remote.responses.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET("discover/movie")
    fun getDiscoverMovies(@Query("page") page : Int) : Single<MovieResponse>
}