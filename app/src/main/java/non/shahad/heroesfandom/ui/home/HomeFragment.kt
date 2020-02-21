package non.shahad.heroesfandom.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.di.Injectable
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.ui.home.models.Home
import non.shahad.heroesfandom.ui.home.models.ParentComic
import non.shahad.heroesfandom.ui.home.models.ParentPublisher
import non.shahad.heroesfandom.utils.extensions.reObserve
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment(),Injectable {
    @Inject
    lateinit var viewmodelFactory : ViewModelFactory

    lateinit var homeViewmodel : HomeViewModel

    private lateinit var homeDelegate : HomeAdapterDelegate

    private val itemList = ArrayList<Home>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewmodel = ViewModelProviders.of(this,viewmodelFactory).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerview()
        homeViewmodel.loadComic("marvel",1)
        homeViewmodel.loadcomicByTag("indie-week",1)
        homeViewmodel.loadPublisher(true)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewmodel.comicLiveData.reObserve(this, Observer {
            Timber.tag("tagg_").d("changes")
            itemList.add(Home(id = 1,title = it.categoryName,
                viewTypes = ViewTypes.COMIC, parentComic = ParentComic(list = it.list),parentPublisher = null))
            homeDelegate.items = itemList

        })

        homeViewmodel.publisherLiveData.reObserve(this, Observer {
            itemList.add(Home(id = 2,title = "Publishers",
                viewTypes = ViewTypes.PUBLISHER,  parentPublisher = ParentPublisher(list = it), parentComic = null))
            homeDelegate.items = itemList

        })

        homeViewmodel.comicByTag.reObserve(this, Observer {
            Timber.tag("tagg_").d("changes")
            itemList.add(Home(id = 3,title = it.categoryName,
                viewTypes = ViewTypes.COMIC,parentComic =  ParentComic(list = it.list),parentPublisher = null))

            homeDelegate.items = itemList

        })

    }

    private fun setUpRecyclerview(){
        homeDelegate = HomeAdapterDelegate(context!!)

        homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = homeDelegate
        }
    }

    companion object{
        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

}
