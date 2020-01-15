package non.shahad.heroesfandom.domain.datasources.heroes

import androidx.lifecycle.LiveData
import non.shahad.heroesfandom.data.local.daos.SuperHeroDao
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import javax.inject.Inject

class HeroesLocalDataSource @Inject constructor(
    val superHeroDao: SuperHeroDao
) {

    fun getAllHeroes() : LiveData<List<HeroEntity>> = superHeroDao.getAllHeroes()

    fun insert(heroList: List<HeroEntity>) = superHeroDao.deleteAndInsertAll(heroList)

}