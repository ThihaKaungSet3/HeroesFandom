package non.shahad.heroesfandom.ui.movies
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.databinding.FragmentMoviesBinding
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.ui.movies.models.Banner
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import non.shahad.heroesfandom.utils.domain.Status
import non.shahad.heroesfandom.utils.extensions.reObserve
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject
import kotlin.math.log

class MoviesFragment : BaseFragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    @Inject
    lateinit var viewModelFactory : ViewModelFactory
    private lateinit var viewBinding : FragmentMoviesBinding
    private lateinit var adapterDelegate : MainMovieDelegateAdapter


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
        moviesViewModel.postMoviePage(1)


//        moviesViewModel.heroesLivedata.reObserve(this, Observer {
//            val bannerList = moviesViewModel.prepareMoviesForBanner(it)
//            itemList.add(MainMovies(1,ViewTypes.BANNER,null,bannerList))
//            itemList.add(MainMovies(2,ViewTypes.MOVIES,it,null,"Discover"))
//            moviesViewModel.fetchTrendingMovies()
//        })
//



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()


    }

    private fun setUpRecyclerView(){
        adapterDelegate = MainMovieDelegateAdapter(context!!)

        viewBinding.movieMainRecycler.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = adapterDelegate
            setHasFixedSize(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        moviesViewModel.loadDiscoverMovie(2)

        /**
         * First load Discover
         */
        moviesViewModel.discoverMovieLiveData.reObserve(this, Observer {
            when(it.status){
                Status.LOADING -> {
//                    viewBinding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    moviesViewModel.clearList()
                    moviesViewModel.addMovies(it.data!!)

                    adapterDelegate.items = moviesViewModel.memoryCache
                    moviesViewModel.fetchTrendingMovies()
                }

                Status.ERROR -> {
                    Toast.makeText(context,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })

        /**
         * Second Load Trending
         */
        moviesViewModel.trendingLiveData.reObserve(this, Observer {
            when(it.status){
                Status.LOADING -> {
//                    viewBinding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    if (!moviesViewModel.isAlreadyContain(it.data!!)){
                        moviesViewModel.addMovies(it.data!!)
                    }

                    adapterDelegate.items = moviesViewModel.memoryCache
                }

                Status.ERROR -> {
                    Toast.makeText(context,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }


    companion object{
        fun newInstance() : MoviesFragment {
            return MoviesFragment()
        }
    }





}
