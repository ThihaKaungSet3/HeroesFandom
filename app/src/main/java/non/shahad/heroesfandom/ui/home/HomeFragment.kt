package non.shahad.heroesfandom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.di.Injectable
import non.shahad.heroesfandom.utils.extensions.timber
import non.shahad.heroesfandom.utils.recyclerviewutils.RecyclerViewPaginator

class HomeFragment : BaseFragment(),Injectable {

    private val homeViewmodel by injectViewModels<HomeViewModel>()

    private lateinit var homeDelegate : HomeAdapterDelegation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        setUpRecyclerview()

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewmodel.fetchHomeData(1)

        homeViewmodel.homeResponse.observe(viewLifecycleOwner, Observer {
            homeDelegate.insertItems(lifecycleScope,it)
        })

    }

    private fun setUpRecyclerview(){
        homeDelegate = HomeAdapterDelegation(context!!)

        homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = homeDelegate

            RecyclerViewPaginator(
                this,
                isLoading =  {homeViewmodel.isLoading},
                loadMore = {
                    homeViewmodel.fetchHomeData(it)
                    timber("HOme_","$it")
                },
                onLast = {homeViewmodel.isLast}
            ).apply {
                threshold = 1
                currentPage = 1
            }
        }
    }

    companion object{
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

}
