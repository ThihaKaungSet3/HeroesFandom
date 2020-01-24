package non.shahad.heroesfandom.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import non.shahad.heroesfandom.di.ViewModelFactory
import non.shahad.heroesfandom.di.ViewmodelKey
import non.shahad.heroesfandom.ui.heroes.HeroesViewModel
import non.shahad.heroesfandom.ui.home.HomeViewModel
import non.shahad.heroesfandom.ui.movies.MoviesViewModel

@Module
abstract class ViewModelModules {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewmodelKey(HeroesViewModel::class)
    abstract fun provideHeroesViewModel(heroesViewModel: HeroesViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewmodelKey(MoviesViewModel::class)
    abstract fun provideMoviesViewModel(heroesViewModel: MoviesViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewmodelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

}