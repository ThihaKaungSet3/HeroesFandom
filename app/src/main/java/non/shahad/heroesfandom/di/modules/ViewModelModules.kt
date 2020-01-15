package non.shahad.heroesfandom.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.di.ViewmodelKey
import non.shahad.heroesfandom.ui.heroes.HeroesViewModel

@Module
abstract class ViewModelModules {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewmodelKey(HeroesViewModel::class)
    abstract fun provideSplashFragmentViewModel(heroesViewModel: HeroesViewModel): ViewModel

}