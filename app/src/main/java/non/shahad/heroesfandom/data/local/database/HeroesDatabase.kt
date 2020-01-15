package non.shahad.heroesfandom.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import non.shahad.heroesfandom.data.local.converters.IntegerListConverter
import non.shahad.heroesfandom.data.local.converters.StringListConverter
import non.shahad.heroesfandom.data.local.daos.MoviesDao
import non.shahad.heroesfandom.data.local.daos.SuperHeroDao
import non.shahad.heroesfandom.data.local.entities.*

@Database(
    entities = [
        HeroEntity::class,
        MovieEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    StringListConverter::class,
    IntegerListConverter::class
)
abstract class HeroesDatabase : RoomDatabase(){
    abstract fun superHeroDao() : SuperHeroDao

    abstract fun moviesDao() : MoviesDao
}