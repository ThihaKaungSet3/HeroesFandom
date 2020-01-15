package non.shahad.heroesfandom.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import non.shahad.heroesfandom.ui.MainActivity
import non.shahad.heroesfandom.di.scopes.PerActivity

@Module(includes = [ViewModelModules::class])
abstract class ActivityModule{

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun mainActivity(): MainActivity
}