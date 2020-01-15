package non.shahad.heroesfandom

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.heroesfandom.di.components.DaggerAppComponent
import timber.log.Timber

class HeroApplication : DaggerApplication(){

    private val appComponent = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
//        Stetho.initializeWithDefaults(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

}