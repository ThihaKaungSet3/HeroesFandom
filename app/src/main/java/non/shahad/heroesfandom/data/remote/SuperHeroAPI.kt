package non.shahad.heroesfandom.data.remote

import io.reactivex.Single
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import retrofit2.http.GET

interface SuperHeroAPI {

    @GET("akabab/superhero-api/0.2.0/api/all.json")
    fun getAllHeroes() : Single<List<HeroEntity>>
}