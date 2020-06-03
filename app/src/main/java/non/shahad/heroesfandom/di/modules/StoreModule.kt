package non.shahad.heroesfandom.di.modules

import com.dropbox.android.external.store4.*
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import non.shahad.heroesfandom.data.db.daos.ComicDao
import non.shahad.heroesfandom.data.db.daos.MovieNewsDao
import non.shahad.heroesfandom.data.db.daos.MoviesDao
import non.shahad.heroesfandom.data.db.entities.ComicEntity
import non.shahad.heroesfandom.data.db.entities.MovieEntity
import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity
import non.shahad.heroesfandom.data.db.entities.NewsEntity
import non.shahad.heroesfandom.data.remote.HeroesFandomAPI
import non.shahad.heroesfandom.data.remote.MoviesAPI
import non.shahad.heroesfandom.data.remote.responses.HomeDataResponse
import non.shahad.heroesfandom.utils.config.ComicConfig
import non.shahad.heroesfandom.utils.config.MovieConfig
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import kotlin.time.ExperimentalTime

@Module
class StoreModule {

    @ExperimentalCoroutinesApi
    @FlowPreview
    @ExperimentalTime
    @Singleton
    @Provides
    @Named("universeComicStore")
    fun provideComicByUniverseStore(
        api: HeroesFandomAPI,
        db: ComicDao
    ) : Store<Pair<Int,ComicConfig>,List<ComicEntity>>{
        return StoreBuilder
            .fromNonFlow<Pair<Int,ComicConfig>,List<ComicEntity>> {
                api.getComicByUniverse(it.second.universe,it.first).posts
            }.persister(
                reader = {(page,config) -> db.getComicsByUniverse(config.universe,page)},
                writer = {(page,config),comics -> db.insertComicsByUniverse(config.universe,page,comics)},
                delete = {(page,config) -> db.clearComicsAssociatedWithUniverse(config.universe,page)},
                deleteAll = db::deleteAll
            )
            .cachePolicy(
                MemoryPolicy.builder()
                    .setExpireAfterAccess(15)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)// or setExpireAfterWrite(10.minutes)
                    .build()
            )
            .build()
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Provides
    @Singleton
    @Named("trendingMovieStore")
    fun provideTrendingMovieStore(
        api: MoviesAPI
    ) : Store<Pair<Int,MovieConfig>,List<MovieEntity>> {
        return StoreBuilder
            .fromNonFlow<Pair<Int,MovieConfig>,List<MovieEntity>> {
                api.getTrendingMovies().results
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setExpireAfterAccess(30)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)// or setExpireAfterWrite(10.minutes)
                    .build()
            )
            .build()
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Provides
    @Singleton
    @Named("discoverMovieStore")
    fun provideDiscoverMovieStore(
        api: MoviesAPI
    ) : Store<Pair<Int,MovieConfig>,List<MovieEntity>> {
        return StoreBuilder
            .fromNonFlow<Pair<Int,MovieConfig>,List<MovieEntity>> {
                api.getDiscoverMovies(it.first).results
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(10)
                    .setExpireAfterAccess(30)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)// or setExpireAfterWrite(10.minutes)
                    .build()
            )
            .build()
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Provides
    @Singleton
    @Named("movieNewsStore")
    fun provideMovieNewsStore(
        api: HeroesFandomAPI
    ) : Store<Int,List<MovieNewsEntity>>{
        return StoreBuilder
            .fromNonFlow<Int,List<MovieNewsEntity>> {
                api.getMovieNews().news
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setExpireAfterAccess(3)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)// or setExpireAfterWrite(10.minutes)
                    .build()
            )
            .build()
    }


    @Provides
    @Singleton
    @Named("comicNewsStore")
    fun provideComicNewsStore(
        api: HeroesFandomAPI
    ) : Store<Int,List<NewsEntity>>{
        return StoreBuilder
            .fromNonFlow<Int,List<NewsEntity>> {
                api.getNews().latestNews
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(10)
                    .setExpireAfterAccess(15)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)  // or setExpireAfterWrite(10.minutes)
                    .build()
            )
            .build()

    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Provides
    @Singleton
    @Named("homeDataStore")
    fun provideHomeDataStore(
        api: HeroesFandomAPI
    ) : Store<Int,HomeDataResponse>{
        return StoreBuilder
            .fromNonFlow<Int,HomeDataResponse> {
                api.getHomeData(it)
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(10)
                    .setExpireAfterAccess(15)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)  // or setExpireAfterWrite(10.minutes)
                    .build()
            )
            .build()

    }

}