package non.shahad.heroesfandom.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.local.daos.MoviesDao
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.data.remote.MoviesAPI
import non.shahad.heroesfandom.data.remote.responses.MovieResponse
import non.shahad.heroesfandom.utils.domain.RateLimiter
import non.shahad.heroesfandom.utils.domain.Resource
import non.shahad.heroesfandom.utils.domain.Status
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MoviesRepository @Inject constructor(val moviesDao: MoviesDao,val moviesAPI: MoviesAPI){

    private val rateLimiter = RateLimiter<String>(5, TimeUnit.MINUTES)

    private var disposable : Disposable? = null

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

    fun loadTrendingMovies() : LiveData<List<MovieEntity>>{
        val liveData : MutableLiveData<List<MovieEntity>> = MutableLiveData()
        moviesAPI.getTrendingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieResponse>{
                override fun onSuccess(t: MovieResponse) {
                    liveData.value = t.results
                    disposable?.dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    disposable?.dispose()
                }

            })

        return liveData
    }
}