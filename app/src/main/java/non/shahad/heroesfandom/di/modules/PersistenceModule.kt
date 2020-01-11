package non.shahad.heroesfandom.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.local.daos.SuperHeroDao
import non.shahad.heroesfandom.data.local.database.HeroesDatabase
import javax.inject.Singleton

@Module
class PersistenceModule{

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context) : HeroesDatabase {
        return Room
            .databaseBuilder(context
                ,HeroesDatabase::class.java,Constants.Persistence.DATABASE_NAME)
            .build()
    }


    @Singleton
    @Provides
    fun provideSuperHeroDao(db: HeroesDatabase) : SuperHeroDao = db.superHeroDao()


}