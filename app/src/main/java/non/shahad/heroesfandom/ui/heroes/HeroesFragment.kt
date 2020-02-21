package non.shahad.heroesfandom.ui.heroes

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.databinding.FragmentHeroesBinding
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.ui.herodetail.HeroDetailFragment
import non.shahad.heroesfandom.utils.custom.RecyclerViewPaginator
import non.shahad.heroesfandom.utils.domain.Status
import non.shahad.heroesfandom.utils.extensions.reObserve
import timber.log.Timber
import javax.inject.Inject

class HeroesFragment : BaseFragment(),HeroesAdapter.HeroSelectListener {

    private lateinit var viewBinding : FragmentHeroesBinding
    private lateinit var heroesViewModel: HeroesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var heroAdapter : HeroesAdapter
    private var isLoading : Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_heroes, container, false)
        heroesViewModel = ViewModelProviders.of(this,viewModelFactory).get(HeroesViewModel::class.java)
        retainInstance = true
        setHasOptionsMenu(true)


        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startRecyclerView()
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar2)
        (activity as AppCompatActivity).title = ""

        heroesViewModel.fetchAllHeroes(true)

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        heroesViewModel.allHeroes.reObserve(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    viewBinding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    viewBinding.progressBar.visibility = View.GONE
                    heroAdapter.setHeroesList(it.data!!)
                }
                Status.ERROR -> {
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }
        });

        heroesViewModel.filteredHeroes.reObserve(this, Observer {
//            heroAdapter.setHeroesList(it)
        })
    }

    /**
     * I didn't use emptyList because
     * I need to mutate list for DiffUtil
     */
    private fun startRecyclerView(){
        heroAdapter = HeroesAdapter(ArrayList(),this)

        viewBinding.heroesRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = heroAdapter
            hasFixedSize()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.heroes_toolbar_item,menu)
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.tag("autumnsong").d("${heroAdapter.itemCount}")
                heroesViewModel.findHeros(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isEmpty()){
                    heroesViewModel.fetchAllHeroes(true)
                }
                return false
            }

        })


    }

    override fun onHeroSelect(hero: HeroEntity, imageView: ImageView) {
        val bundle = Bundle()
        bundle.putParcelable("hero",hero)

        val heroDetailFragment = HeroDetailFragment.newInstance()
        heroDetailFragment.arguments = bundle
//        Timber.tag("something").d("$mFragmentNavigation")
        mFragmentNavigation.pushFragment(heroDetailFragment)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    

    companion object{
        fun newInstance() : HeroesFragment{
            return HeroesFragment()
        }
    }




}
