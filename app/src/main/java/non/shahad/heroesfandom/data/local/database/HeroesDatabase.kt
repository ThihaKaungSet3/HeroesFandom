package non.shahad.heroesfandom.data.local.database

import androidx.room.RoomDatabase
import non.shahad.heroesfandom.data.local.daos.SuperHeroDao
import non.shahad.heroesfandom.data.remote.SuperHeroAPI

abstract class HeroesDatabase : RoomDatabase(){
    abstract fun superHeroDao() : SuperHeroDao
}