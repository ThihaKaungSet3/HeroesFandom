package non.shahad.heroesfandom.domain.usecases

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.local.daos.MoviesDao
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.data.remote.MoviesAPI
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val moviesDao: MoviesDao,
    private val moviesAPI: MoviesAPI
){

    fun getLocalMoviesByCategoryName(name : String,
                                     page : Int) : Observable<List<MovieEntity>>{
        return Observable.defer { Observable.just(moviesDao.getMoviesByCategoryName(name,page)) }
            .subscribeOn(Schedulers.computation())
    }

    fun getLocalTrendingMovie() : Observable<List<MovieEntity>>{
        return Observable.defer { Observable.just(moviesDao.getTrendingMovies()) }
            .subscribeOn(Schedulers.computation())
    }

    fun getRemoteTrendingMovie() : Observable<List<MovieEntity>>{
        return moviesAPI.getTrendingMovies()
            .subscribeOn(Schedulers.io())
            .map {
                val result = it.results

                result.forEach {movie ->
                    movie.category = Constants.NetworkService.DISCOVER
                }

                result
            }
    }

    fun getRemoteMoviesByCategoryName(name: String,
                                      page : Int) : Observable<List<MovieEntity>>{
        return moviesAPI.getDiscoverMovies(1)
            .map {
                val results = it.results
            for (movie in results){
                movie.page = page
                movie.category = name
            }
            moviesDao.deleteSpecificMovies(name,page)
            moviesDao.insertMoviesList(results)

            results
        }.subscribeOn(Schedulers.io())
    }

}