package non.shahad.heroesfandom.ui.movies
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.databinding.FragmentMoviesBinding
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.utils.custom.RecyclerViewPaginator
import non.shahad.heroesfandom.utils.domain.Status
import non.shahad.heroesfandom.utils.extensions.reObserve
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    @Inject
    lateinit var viewModelFactory : ViewModelFactory
    private lateinit var viewBinding : FragmentMoviesBinding
    private lateinit var adapter: MoviesAdapter
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movies,container,false)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProviders.of(this,viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        moviesViewModel.postMoviePage(1)

    }

    private fun setUpRecyclerView(){
        adapter = MoviesAdapter(ArrayList())
        viewBinding.apply {
            movieRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            movieRecycler.adapter = adapter

            movieRecycler.addOnScrollListener(object : RecyclerViewPaginator(movieRecycler){
                override fun isLastPage(): Boolean {
                    return false
                }

                override fun loadMore(page: Int) {
                    moviesViewModel.postMoviePage(page)
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

            })

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            moviesViewModel.movieListLiveData.reObserve(this, Observer {
                when(it.status){
                    Status.LOADING -> {
                        isLoading = true
//                    viewBinding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        adapter.addMovies(it.data!!)
                        isLoading = false
                    }
                    Status.ERROR -> {
                        Toast.makeText(context,"Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }

            })


    }

    override fun onStart() {
        super.onStart()

    }

    override fun onPause() {
        super.onPause()
    }





}
