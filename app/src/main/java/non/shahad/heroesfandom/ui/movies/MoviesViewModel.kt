package non.shahad.heroesfandom.ui.movies

import androidx.lifecycle.*
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.repositories.MoviesRepository
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import non.shahad.heroesfandom.utils.domain.Resource
import java.util.ArrayList
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel(){

    private val moviePageLiveData : MutableLiveData<Int> = MutableLiveData()

    val memoryCache : MutableList<MainMovies> = ArrayList()

    var discoverMovieLiveData : LiveData<Resource<List<MainMovies>>>
    var discoverPageLiveData : MutableLiveData<Int> =  MutableLiveData()
//    var heroesLivedata : LiveData<List<MovieEntity>>
    var trendingLiveData : LiveData<Resource<List<MainMovies>>>


    var currentPage : MutableLiveData<Int> = MutableLiveData()
    var shouldFetchTrending : MutableLiveData<Boolean> = MutableLiveData()


    init {
        this.discoverMovieLiveData = Transformations.switchMap(discoverPageLiveData){
            moviesRepository.loadMoviesByCategoryName(Constants.NetworkService.DISCOVER,it)
        }
//        this.heroesLivedata = Transformations.switchMap(moviePageLiveData){
//            moviesRepository.loadMoviesByCategoryName(it)
//        }

        this.trendingLiveData = Transformations.switchMap(shouldFetchTrending){
            moviesRepository.loadTrendingMovies()
        }

        this.currentPage.value = 1
    }

    fun addMovies(list: List<MainMovies>){
        memoryCache.addAll(list)
    }

    fun clearList(){
        memoryCache.clear()
    }

     fun isAlreadyContain(list: List<MainMovies>) : Boolean{
        memoryCache.forEach{cache ->
            list.forEach{ movie ->
                if (movie.id == cache.id){
                    return true
                }
            }
        }
        return false
    }

    fun loadDiscoverMovie(page: Int) = this.discoverPageLiveData.postValue(page)
    fun fetchTrendingMovies() = this.shouldFetchTrending.postValue(true)
    fun postMoviePage(page : Int) = this.moviePageLiveData.postValue(page)




}