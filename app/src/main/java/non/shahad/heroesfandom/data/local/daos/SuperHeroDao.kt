package non.shahad.heroesfandom.data.local.daos

import androidx.room.Insert
import non.shahad.heroesfandom.data.local.entities.HeroEntity

interface SuperHeroDao{
    @Insert
    fun insert(heroEntity: HeroEntity)

}