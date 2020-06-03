package non.shahad.heroesfandom.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Single
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.db.daos.SuperHeroDao
import non.shahad.heroesfandom.data.db.entities.HeroEntity
import non.shahad.heroesfandom.data.remote.SuperHeroAPI
import non.shahad.heroesfandom.utils.domain.RateLimiter
import non.shahad.heroesfandom.utils.domain.Resource
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val heroDao: SuperHeroDao,
    private val superHeroAPI: SuperHeroAPI
) {

    private val rateLimiter = RateLimiter<String>(5,TimeUnit.MINUTES)

    // TODO need to modify shouldfetch with ratelimiter
    fun getAllHeroes() : LiveData<Resource<List<HeroEntity>>>{
        return object : NetworkBoundResource<List<HeroEntity>,List<HeroEntity>>(){
            override fun saveCallResult(item: List<HeroEntity>) = heroDao.insertAllheroes(item)

            override fun shouldFetch(data: List<HeroEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<HeroEntity>> = heroDao.getAllHeroes()

            override fun createCall(): Single<List<HeroEntity>> = superHeroAPI.getAllHeroes()

            override fun onFetchFailed() = rateLimiter.reset(Constants.NetworkService.RATE_LIMITER_TYPE)

        }.asLiveData
    }


    fun findHeroByName(name : String) : LiveData<List<HeroEntity>>{
        Timber.tag("autumnsong").d("$name")
        return heroDao.findHeroByName(name)
    }


}