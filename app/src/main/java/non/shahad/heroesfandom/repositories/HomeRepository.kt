package non.shahad.heroesfandom.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.local.entities.ComicEntity
import non.shahad.heroesfandom.data.remote.GetComicAPI
import non.shahad.heroesfandom.data.remote.PublisherAPI
import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import non.shahad.heroesfandom.data.local.entities.PublisherEntity
import non.shahad.heroesfandom.domain.usecases.HomeUseCase
import non.shahad.heroesfandom.ui.home.models.Home
import non.shahad.heroesfandom.ui.home.models.ParentComic
import non.shahad.heroesfandom.ui.home.models.ParentPublisher
import non.shahad.heroesfandom.utils.ComicPageParser
import non.shahad.heroesfandom.utils.domain.Resource
import okhttp3.ResponseBody
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val api: GetComicAPI,
    private val publisherAPI: PublisherAPI
){
    private var disposable : Disposable? = null

    /**
     * Load publisher
     * Load marvel comics
     * Load Dc Comics
     */
    fun getHomeData() : LiveData<Resource<List<Home>>> {
        val liveData = MutableLiveData<Resource<List<Home>>>()
        val publisers = publisherAPI.getAllPublishers()
        val marvel = api.getComicsByUniverse("marvel",1)
        val dc = api.getComicsByTag("dc-week",2)
        Observable.zip(
            publisers,
            marvel,
            dc,
            Function3<List<PublisherEntity>,ResponseBody,ResponseBody,List<Home>> { t1, t2, t3 ->
                val marvel = ComicPageParser.parse(t2)
                val dc = ComicPageParser.parse(t3)

                return@Function3 listOf<Home>(
                    Home(
                        id = 1,
                        title = "Publishers",
                        viewTypes = ViewTypes.PUBLISHER,
                        parentPublisher = ParentPublisher(list = t1),
                        parentComic = null
                    ),
                    Home(2, ViewTypes.COMIC, ParentComic("Marvel", marvel.list), null, "Marvel"),
                    Home(3, ViewTypes.COMIC, ParentComic("DC", dc.list), null, "DC")
                )
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Home>> {
                override fun onComplete() {
                    disposable?.dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    liveData.postValue(Resource.loading(null))
                    disposable = d
                }

                override fun onNext(t: List<Home>) {
                    liveData.postValue(Resource.success(t))
                    disposable?.dispose()
                }

                override fun onError(e: Throwable) {
                    liveData.postValue(Resource.error(e.message!!,null))
                    disposable?.dispose()
                }

            })

        return liveData
    }



}