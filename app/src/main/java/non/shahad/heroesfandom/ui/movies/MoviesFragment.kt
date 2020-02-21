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
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.databinding.FragmentMoviesBinding
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.ui.movies.models.Banner
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import non.shahad.heroesfandom.utils.domain.Status
import non.shahad.heroesfandom.utils.extensions.reObserve
import java.util.ArrayList
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    @Inject
    lateinit var viewModelFactory : ViewModelFactory
    private lateinit var viewBinding : FragmentMoviesBinding
    private lateinit var adapterDelegate : MainMovieDelegateAdapter
    private val itemList : MutableList<MainMovies> = ArrayList()

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
        moviesViewModel.loadMovies(1)


        moviesViewModel.movieListLiveData.reObserve(this, Observer {
            when(it.status){
                Status.LOADING -> {
//                    viewBinding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    val bannerList : ArrayList<Banner> = ArrayList()
                    for (i in 1..4){
                        val movie = it.data?.get(i)!!
                        bannerList.add(Banner(movie.poster_path,movie.title))
                    }

                    itemList.add(MainMovies(1,ViewTypes.BANNER,null,bannerList))
                    itemList.add(MainMovies(2,ViewTypes.MOVIES,it.data,null,"Discover"))


                        adapterDelegate.items = itemList
//                    adapterMain.addItem(MainMovies(ViewTypes.BANNER,null,bannerList))
//                    adapterMain.addItem(MainMovies(ViewTypes.MOVIES,it.data,null,"Discover"))

                }

                Status.ERROR -> {
                    Toast.makeText(context,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })


        moviesViewModel.loadTrendingMovies().reObserve(this, Observer {

            itemList.add( MainMovies(3,ViewTypes.MOVIES,it,null,"Trending"))

            adapterDelegate.items = itemList

        })
    }


    companion object{
        fun newInstance() : MoviesFragment {
            return MoviesFragment()
        }
    }





}
