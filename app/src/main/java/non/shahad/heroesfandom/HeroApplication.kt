package non.shahad.heroesfandom

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.heroesfandom.di.components.DaggerAppComponent
import timber.log.Timber

class HeroApplication : DaggerApplication(){

    private val appComponent = DaggerAppComponent
        .factory().create(this)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

}