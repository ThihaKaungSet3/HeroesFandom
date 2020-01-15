package non.shahad.heroesfandom.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Single
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.domain.datasources.heroes.HeroesLocalDataSource
import non.shahad.heroesfandom.domain.datasources.heroes.HeroesRemoteDataSource
import non.shahad.heroesfandom.utils.domain.RateLimiter
import non.shahad.heroesfandom.utils.domain.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val heroesLocalDataSource: HeroesLocalDataSource,
    private val heroesRemoteDataSource: HeroesRemoteDataSource
) {

    private val rateLimiter = RateLimiter<String>(5,TimeUnit.MINUTES)

    // TODO need to modify shouldfetch with ratelimiter
    fun getAllHeroes() : LiveData<Resource<List<HeroEntity>>>{
        return object : NetworkBoundResource<List<HeroEntity>,List<HeroEntity>>(){
            override fun saveCallResult(item: List<HeroEntity>) = heroesLocalDataSource.insert(item)

            override fun shouldFetch(data: List<HeroEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<HeroEntity>> = heroesLocalDataSource.getAllHeroes()

            override fun createCall(): Single<List<HeroEntity>> = heroesRemoteDataSource.getAllHeroes()

            override fun onFetchFailed() = rateLimiter.reset(Constants.NetworkService.RATE_LIMITER_TYPE)

        }.asLiveData
    }


}