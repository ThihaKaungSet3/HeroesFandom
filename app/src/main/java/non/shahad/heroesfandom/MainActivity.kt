package non.shahad.heroesfandom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import non.shahad.heroesfandom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setUpNavigation()
    }

    private fun setUpNavigation(){
        val navController = Navigation.findNavController(this,R.id.navHostFragment)
        NavigationUI.setupWithNavController(viewBinding.bottomNavigationView,navController)
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this,R.id.nav_graph).navigateUp()

}
