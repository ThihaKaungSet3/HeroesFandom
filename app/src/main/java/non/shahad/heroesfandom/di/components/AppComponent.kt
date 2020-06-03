package non.shahad.heroesfandom.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.heroesfandom.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityModule::class,
    NetModule::class,
    PersistenceModule::class,
    StoreModule::class,
    AndroidInjectionModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication>{

    @Component.Factory
    interface Factory{
        fun create (@BindsInstance application : Application) : AppComponent
    }

}