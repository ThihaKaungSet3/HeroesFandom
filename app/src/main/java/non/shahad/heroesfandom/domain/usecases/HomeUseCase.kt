package non.shahad.heroesfandom.domain.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import non.shahad.heroesfandom.data.local.daos.ComicDao
import non.shahad.heroesfandom.data.local.entities.ComicEntity
import non.shahad.heroesfandom.data.local.entities.PublisherEntity
import non.shahad.heroesfandom.data.remote.GetComicAPI
import non.shahad.heroesfandom.data.remote.PublisherAPI
import non.shahad.heroesfandom.utils.ComicPageParser
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val comicDao: ComicDao,
    private val getComicAPI: GetComicAPI,
    private val publisherAPI : PublisherAPI
) {

    fun getLocalPublishers() : Observable<List<PublisherEntity>>{
        return Observable.defer { Observable.just(comicDao.getPublishers()) }.subscribeOn(Schedulers.computation())
    }

    fun getRemotePublishers() : Observable<List<PublisherEntity>>{
        return publisherAPI.getAllPublishers().subscribeOn(Schedulers.io())
            .doOnNext{
                comicDao.deleteAllPublishers()
                comicDao.savePublishers(it)
            }
    }

    fun getLocalComicByUniverse(universe : String,page : Int) : Observable<List<ComicEntity>>{
        return Observable.defer { Observable.just(comicDao.getComicsByCategory(universe,page)) }
    }

    fun getRemoteComicByUniverse(universe : String,page : Int) : Observable<List<ComicEntity>>{
        return getComicAPI.getComicsByUniverse(universe,page).map{
            ComicPageParser.parse(it)
        }.map {response ->
            val result = response.list
            result.forEach{ comic->
                comic.page = page
                comic.category = universe
            }

            result
        }
    }
}