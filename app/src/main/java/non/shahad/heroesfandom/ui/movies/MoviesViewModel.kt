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


    fun loadMoviesForView() : LiveData<Resource<List<MainMovies>>> {
        return moviesRepository.loadReadyMovies()
    }





}