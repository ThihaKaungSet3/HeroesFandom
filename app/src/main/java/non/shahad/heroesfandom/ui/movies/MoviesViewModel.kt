package non.shahad.heroesfandom.ui.movies

import androidx.lifecycle.*
import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.db.entities.MovieEntity
import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity
import non.shahad.heroesfandom.data.models.MainMovies
import non.shahad.heroesfandom.utils.config.MovieConfig
import non.shahad.heroesfandom.utils.domain.Resource
import non.shahad.heroesfandom.utils.extensions.timber
import javax.inject.Inject
import javax.inject.Named

class MoviesViewModel @Inject constructor(
    @Named("trendingMovieStore")
    private val trendingStore : Store<Pair<Int, MovieConfig>,List<MovieEntity>>,
    @Named("discoverMovieStore")
    private val discoverStore: Store<Pair<Int,MovieConfig>,List<MovieEntity>>,
    @Named("movieNewsStore")
    private val newsStore: Store<Int,List<MovieNewsEntity>>
) : ViewModel(){

    /**
     * Fetch in parallel
     */
    fun loadMoviesForView() : LiveData<Resource<List<MainMovies>>> {
        val liveData = MutableLiveData<Resource<List<MainMovies>>>()
        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))

            withContext(Dispatchers.IO){
                supervisorScope {
                    runCatching {
                        val news = async { fetchMovieNews() }
                        val trending = async { fetchTrendingMovies() }
                        val discover = async { fetchDiscoverMovies() }

                        val mainMovie = listOf<MainMovies>(
                            MainMovies(
                                id = 1,
                                viewTypes = ViewTypes.NEWS,
                                newsItemList = news.await()
                            ),
                            MainMovies(
                                2,
                                ViewTypes.MOVIES,
                                title = "Discover",
                                movieList = discover.await()
                            ),
                            MainMovies(
                                3,
                                ViewTypes.MOVIES,
                                title = "Trending",
                                movieList = trending.await()
                            )
                        )

                        timber("Movie_","${mainMovie}")
                        liveData.postValue(Resource.success(mainMovie))
                    }.onFailure {
                        liveData.postValue(Resource.error(it.message!!,null))
                    }
                }
            }
        }
        return liveData
    }

    private suspend fun fetchMovieNews() : List<MovieNewsEntity>{
         return newsStore.get(1)
    }

    private suspend fun fetchTrendingMovies() : List<MovieEntity>{
            val config = Pair(1,MovieConfig("trending"))
            val cache = trendingStore.get(config)

            if (!cache.isNullOrEmpty()) return cache

        return trendingStore.fresh(config)
    }

    private suspend fun fetchDiscoverMovies() : List<MovieEntity>{

            val config = Pair(1,MovieConfig("discover"))
            val cache = discoverStore.get(config)

            if (!cache.isNullOrEmpty()) return cache

        return discoverStore.fresh(config)
    }




}