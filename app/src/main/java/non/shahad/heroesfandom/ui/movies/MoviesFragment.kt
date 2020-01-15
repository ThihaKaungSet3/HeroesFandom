package non.shahad.heroesfandom.ui.movies


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.utils.extensions.reObserve
import timber.log.Timber
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProviders.of(this,viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        moviesViewModel.loadMovies(1).reObserve(this, Observer {
            Timber.tag("autumnsong").d("$it")
        })
    }


}
