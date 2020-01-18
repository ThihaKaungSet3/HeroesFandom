package non.shahad.heroesfandom.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Single
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.local.daos.MoviesDao
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.data.remote.MoviesAPI
import non.shahad.heroesfandom.data.remote.responses.MovieResponse
import non.shahad.heroesfandom.utils.domain.RateLimiter
import non.shahad.heroesfandom.utils.domain.Resource
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MoviesRepository @Inject constructor(val moviesDao: MoviesDao,val moviesAPI: MoviesAPI){

    private val rateLimiter = RateLimiter<String>(5, TimeUnit.MINUTES)

    var isLoading = true

    fun loadMovies(page : Int) : LiveData<Resource<List<MovieEntity>>>{
        return object : NetworkBoundResource<List<MovieEntity>,MovieResponse>(){
            override fun saveCallResult(item: MovieResponse) {
                Timber.tag("autumnsong").d("$item")
                // Get current page for unique
                val page = item.page
                item.let {
                    // add page to each movieEntity as unique when fetching
                    it.results.forEach { movie ->
                        movie.page = page
                    }
                    // Insert to movie
                    moviesDao.insertMoviesList(it.results)
                }
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean = data!!.isEmpty()

            override fun loadFromDb(): LiveData<List<MovieEntity>> {
                isLoading = true
                return moviesDao.getAllMovies(page = page)
            }

            override fun createCall(): Single<MovieResponse> = moviesAPI.getDiscoverMovies(page = page)

            override fun onFetchFailed() = rateLimiter.reset(Constants.NetworkService.RATE_LIMITER_TYPE)

        }.asLiveData
    }
}