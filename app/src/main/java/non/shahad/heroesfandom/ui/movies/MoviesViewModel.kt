package non.shahad.heroesfandom.ui.movies

import androidx.lifecycle.*
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.repositories.MoviesRepository
import non.shahad.heroesfandom.utils.domain.Resource
import timber.log.Timber
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    val moviesRepository: MoviesRepository
) : ViewModel(){

    private val moviePageLiveData : MutableLiveData<Int> = MutableLiveData()
    val movieListLiveData : LiveData<Resource<List<MovieEntity>>>

    var currentPage : MutableLiveData<Int> = MutableLiveData()

//    val movies = MediatorLiveData<Resource<List<MovieEntity>>>()

    init {
        this.movieListLiveData = Transformations.switchMap(moviePageLiveData) { page ->
            moviesRepository.loadMovies(page)
        }

        this.currentPage.value = 1
    }

    fun loadMovies(page : Int) : LiveData<Resource<List<MovieEntity>>> {
        return moviesRepository.loadMovies(page)
    }

    fun loadTrendingMovies() : LiveData<List<MovieEntity>> = moviesRepository.loadTrendingMovies()

    fun isLoading() = moviesRepository.isLoading

    fun postMoviePage(page : Int) = this.moviePageLiveData.postValue(page)
}