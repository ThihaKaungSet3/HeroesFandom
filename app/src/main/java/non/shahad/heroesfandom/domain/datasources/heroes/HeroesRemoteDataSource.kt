package non.shahad.heroesfandom.domain.datasources.heroes

import io.reactivex.Single
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.data.remote.SuperHeroAPI
import javax.inject.Inject

class HeroesRemoteDataSource @Inject constructor(val superHeroAPI: SuperHeroAPI) {

    fun getAllHeroes() : Single<List<HeroEntity>> = superHeroAPI.getAllHeroes()

}