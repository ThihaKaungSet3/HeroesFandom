package non.shahad.heroesfandom.ui.heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager


import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.databinding.FragmentHeroesBinding
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.utils.domain.Resource
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_heroes, container, false)
        heroesViewModel = ViewModelProviders.of(this,viewModelFactory).get(HeroesViewModel::class.java)
        retainInstance = true
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startRecyclerView()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        heroesViewModel.getAllHeroes().reObserve(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    viewBinding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    viewBinding.progressBar.visibility = View.GONE
                    heroAdapter.addHeroes(newList = it.data!!)
                }
                Status.ERROR -> {
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }
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

    override fun onHeroSelect(hero: HeroEntity, imageView: ImageView) {

        val bundle = Bundle()
        bundle.putParcelable("hero",hero)

        findNavController().navigate(R.id.actionHeroestoHeroDetail,bundle)
    }


}
