package non.shahad.heroesfandom.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.ui.movies.models.Banner
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import non.shahad.heroesfandom.domain.usecases.MovieUseCase
import non.shahad.heroesfandom.utils.domain.Resource
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieUseCase: MovieUseCase
){

    private var disposable : Disposable? = null

    fun loadMoviesByCategoryName(name : String,page : Int) : LiveData<Resource<List<MainMovies>>>{
        val movieLiveData : MutableLiveData<Resource<List<MainMovies>>> = MutableLiveData()

        Observable.concat(
            movieUseCase.getLocalMoviesByCategoryName(name,page).subscribeOn(Schedulers.computation()),
            movieUseCase.getRemoteMoviesByCategoryName(name,page).subscribeOn(Schedulers.io())
        )
            .observeOn(AndroidSchedulers.mainThread(),true)
            .subscribe(object : Observer<List<MovieEntity>>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    movieLiveData.postValue(Resource.loading(null))
                }

                override fun onNext(t: List<MovieEntity>) {
                    val modifiedList = listOf<MainMovies>(
                        MainMovies(1,ViewTypes.BANNER,null,prepareMoviesForBanner(t)),
                        MainMovies(2,ViewTypes.MOVIES,t,null,"Discover")
                    )
                    movieLiveData.value = Resource.success(modifiedList)
                }

                override fun onError(e: Throwable) {
                    movieLiveData.postValue(Resource.error(e.message!!,null))
                    disposable?.dispose()
                }

            })

        return movieLiveData
    }

    fun prepareMoviesForBanner(movieList : List<MovieEntity>) : List<Banner>{
        val bannerList = arrayListOf<Banner>()
        for(i in 0..4){
            val movie = movieList[i]
            bannerList.add(Banner(movie.poster_path,movie.title))
        }

        return bannerList
    }

    fun loadTrendingMovies() : LiveData<Resource<List<MainMovies>>>{
        val movieLiveData : MutableLiveData<Resource<List<MainMovies>>> = MutableLiveData()

        Observable.concat(
            movieUseCase.getLocalTrendingMovie(),
            movieUseCase.getRemoteTrendingMovie()
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MovieEntity>>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    movieLiveData.postValue(Resource.loading(null))
                }

                override fun onNext(t: List<MovieEntity>) {
                    movieLiveData.value = Resource.success(listOf(
                        MainMovies(3,ViewTypes.MOVIES,t,null,"Discover")
                    ))
                }

                override fun onError(e: Throwable) {
                    movieLiveData.postValue(Resource.error(e.message!!,null))
                    disposable?.dispose()
                }
            })
        return movieLiveData
    }



}