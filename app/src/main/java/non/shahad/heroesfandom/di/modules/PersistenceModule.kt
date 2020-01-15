package non.shahad.heroesfandom.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.local.daos.MoviesDao
import non.shahad.heroesfandom.data.local.daos.SuperHeroDao
import non.shahad.heroesfandom.data.local.database.HeroesDatabase
import javax.inject.Singleton

@Module
class PersistenceModule{

    @Singleton
    @Provides
    fun provideRoomDatabase(application : Application) : HeroesDatabase {
        return Room
            .databaseBuilder(application
                ,HeroesDatabase::class.java,Constants.Persistence.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideSuperHeroDao(db: HeroesDatabase) : SuperHeroDao = db.superHeroDao()

    @Singleton
    @Provides
    fun provideMoviesDao(db : HeroesDatabase) : MoviesDao = db.moviesDao()


}