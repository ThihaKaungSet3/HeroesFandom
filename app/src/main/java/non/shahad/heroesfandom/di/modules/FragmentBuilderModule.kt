package non.shahad.heroesfandom.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import non.shahad.heroesfandom.data.local.database.HeroesDatabase
import non.shahad.heroesfandom.ui.heroes.HeroesFragment
import non.shahad.heroesfandom.ui.home.HomeFragment
import non.shahad.heroesfandom.ui.movies.MoviesFragment
import non.shahad.heroesfandom.ui.setting.SettingFragment

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeHeroesFragment(): HeroesFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingFragment(): SettingFragment
}