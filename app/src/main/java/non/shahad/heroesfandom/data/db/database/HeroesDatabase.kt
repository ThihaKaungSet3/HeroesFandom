package non.shahad.heroesfandom.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import non.shahad.heroesfandom.data.db.converters.IntegerListConverter
import non.shahad.heroesfandom.data.db.converters.StringListConverter
import non.shahad.heroesfandom.data.db.daos.*
import non.shahad.heroesfandom.data.db.entities.*

@Database(
    entities = [
        HeroEntity::class,
        MovieEntity::class,
        PublisherEntity::class,
        ComicEntity::class,
        MovieNewsEntity::class
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

    abstract fun comicDao() : ComicDao

    abstract fun comicNewsDao() : ComicNewsDao

    abstract fun movieNewsDao() : MovieNewsDao
    
}