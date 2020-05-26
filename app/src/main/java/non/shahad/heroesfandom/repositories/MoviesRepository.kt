package non.shahad.heroesfandom.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.data.remote.MoviesAPI
import non.shahad.heroesfandom.ui.movies.models.Banner
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import non.shahad.heroesfandom.domain.usecases.MovieUseCase
import non.shahad.heroesfandom.utils.domain.Resource
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: MoviesAPI
){

    private var disposable : Disposable? = null

    fun prepareMoviesForBanner(movieList : List<MovieEntity>) : List<Banner>{
        val bannerList = arrayListOf<Banner>()
        for(i in 0..4){
            val movie = movieList[i]
            bannerList.add(Banner(movie.poster_path,movie.title))
        }

        return bannerList
    }

    fun loadReadyMovies() : LiveData<Resource<List<MainMovies>>>{
        val livedata = MutableLiveData<Resource<List<MainMovies>>>()

        Observable.zip(
            api.getDiscoverMovies(1).map { it.results },
            api.getTrendingMovies().map { it.results },
            BiFunction <List<MovieEntity>,List<MovieEntity>,List<MainMovies>>{ t1, t2 ->
                return@BiFunction listOf<MainMovies>(
                    MainMovies(1,ViewTypes.BANNER,null,prepareMoviesForBanner(t1)),
                    MainMovies(2,ViewTypes.MOVIES,t1,null,"Discover"),
                    MainMovies(3,ViewTypes.MOVIES,t2,null,"Trending")
                )
            }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MainMovies>>{
                override fun onComplete() {
                    disposable?.dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: List<MainMovies>) {
                    Log.d("Movie_", "onNext: $t")
                    livedata.postValue(Resource.success(t))
                }

                override fun onError(e: Throwable) {
                    livedata.postValue(Resource.error(e.message!!,null))
                    disposable?.dispose()
                }

            })

        return livedata
    }



}