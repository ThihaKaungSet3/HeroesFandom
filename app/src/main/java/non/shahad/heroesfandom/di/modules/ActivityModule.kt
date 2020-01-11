package non.shahad.heroesfandom.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import non.shahad.heroesfandom.MainActivity
import non.shahad.heroesfandom.di.scopes.PerActivity

@Module
abstract class ActivityModule{

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun mainActivity(): MainActivity
}