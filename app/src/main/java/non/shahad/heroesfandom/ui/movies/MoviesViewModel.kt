package non.shahad.heroesfandom.ui.movies

import androidx.lifecycle.ViewModel
import non.shahad.heroesfandom.repositories.MoviesRepository
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    val moviesRepository: MoviesRepository
) : ViewModel(){

    fun loadMovies(page : Int) = moviesRepository.loadMovies(page)
}