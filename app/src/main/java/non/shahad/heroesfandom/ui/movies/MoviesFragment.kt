package non.shahad.heroesfandom.ui.movies
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.InternalCoroutinesApi
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.databinding.FragmentMoviesBinding
import non.shahad.heroesfandom.utils.domain.Status
import non.shahad.heroesfandom.utils.extensions.timber

class MoviesFragment : BaseFragment() {

    private val moviesViewModel by injectViewModels<MoviesViewModel>()

    private lateinit var viewBinding : FragmentMoviesBinding
    private lateinit var adapterDelegate : MainMovieAdapterDelegation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movies,container,false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){
        adapterDelegate = MainMovieAdapterDelegation(context!!)

        viewBinding.movieMainRecycler.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = adapterDelegate
            setHasFixedSize(true)
        }
    }

    @InternalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesViewModel.loadMoviesForView().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING -> {}
                Status.ERROR -> {
                    timber("Movie_","${it.message!!}")
                }
                Status.SUCCESS -> {
                    adapterDelegate.items = it.data!!
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
