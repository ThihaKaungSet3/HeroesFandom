package non.shahad.heroesfandom.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import me.ibrahimsn.lib.OnItemSelectedListener
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseActivity
import non.shahad.heroesfandom.core.BaseFragment
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.databinding.ActivityMainBinding
import non.shahad.heroesfandom.ui.heroes.HeroesFragment
import non.shahad.heroesfandom.ui.home.HomeFragment
import non.shahad.heroesfandom.ui.library.LibraryFragment
import non.shahad.heroesfandom.ui.movies.MoviesFragment
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(),HasAndroidInjector,
    BaseFragment.FragmentNavigation,
    FragNavController.RootFragmentListener,FragNavController.TransactionListener {
    private val viewBinding by binding<ActivityMainBinding>(R.layout.activity_main)

    override val numberOfRootFragments: Int = 4

    val navController: FragNavController =
        FragNavController(supportFragmentManager, R.id.navContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        changeStatusBarColor(R.color.colorPrimary)

        AndroidInjection.inject(this)

        setUpNavigation()
        setUpBottomNav()

        navController.initialize(Constants.BottomNav.INDEX_HOME, savedInstanceState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        navController.onSaveInstanceState(outState)
    }


    fun changeStatusBarColor(color: Int) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

    private fun setUpNavigation() {
        navController.apply {
            transactionListener = this@MainActivity
            rootFragmentListener = this@MainActivity
            createEager = false
            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {

                }
            }
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH
        }

    }

    private fun setUpBottomNav() {
        viewBinding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> {
                    navController.switchTab(Constants.BottomNav.INDEX_HOME)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionMovies -> {
                    navController.switchTab(Constants.BottomNav.INDEX_MOVIES)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionHeroes -> {
                    navController.switchTab(Constants.BottomNav.INDEX_HEROES)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionLibrary -> {
                    navController.switchTab(Constants.BottomNav.INDEX_LIBRARY)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }


    override fun getRootFragment(index: Int): Fragment {
         when(index){
            Constants.BottomNav.INDEX_HOME -> return HomeFragment.newInstance()
            Constants.BottomNav.INDEX_MOVIES -> return MoviesFragment.newInstance()
            Constants.BottomNav.INDEX_HEROES -> return HeroesFragment.newInstance()
            Constants.BottomNav.INDEX_LIBRARY -> return LibraryFragment.newInstance()
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {

    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        when(index){
            Constants.BottomNav.INDEX_HOME -> viewBinding.bottomNav.selectedItemId = R.id.actionHome
            Constants.BottomNav.INDEX_MOVIES -> viewBinding.bottomNav.selectedItemId = R.id.actionMovies
            Constants.BottomNav.INDEX_HEROES -> viewBinding.bottomNav.selectedItemId = R.id.actionHeroes
            Constants.BottomNav.INDEX_LIBRARY -> viewBinding.bottomNav.selectedItemId = R.id.actionLibrary
        }

    }

    override fun pushFragment(fragment: Fragment, sharedElementList: List<Pair<View, String>>?) {
//        val options = FragNavTransactionOptions.newBuilder()
        navController.pushFragment(fragment)
    }

}
