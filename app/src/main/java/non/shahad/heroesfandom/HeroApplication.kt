package non.shahad.heroesfandom

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.heroesfandom.di.components.DaggerAppComponent

class HeroApplication : DaggerApplication(){

    private val appComponent = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

}