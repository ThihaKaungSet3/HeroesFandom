package non.shahad.heroesfandom.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseActivity
import non.shahad.heroesfandom.databinding.ActivityMainBinding
import non.shahad.heroesfandom.utils.extensions.setupWithNavController
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(),HasAndroidInjector {
    private lateinit var viewBinding : ActivityMainBinding

    private var currentNavController : LiveData<NavController>? = null
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        AndroidInjection.inject(this)

        if(savedInstanceState == null){
            setUpNavigation()
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpNavigation()
    }

    private fun setUpNavigation(){
        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.movies,
            R.navigation.heroes,
            R.navigation.library
        )

        val controller = viewBinding.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navContainer,
            intent = intent

        )

        controller.observe(this, Observer {
            Timber.tag("autumnsong").d("$it")
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    fun changeStatusBarColor(color : Int){
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color

    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}
