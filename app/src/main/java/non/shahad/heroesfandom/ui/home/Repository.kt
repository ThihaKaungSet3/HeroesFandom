package non.shahad.heroesfandom.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import non.shahad.heroesfandom.data.remote.GetComicAPI
import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import non.shahad.heroesfandom.domain.model.Comic
import non.shahad.heroesfandom.domain.model.Publisher
import non.shahad.heroesfandom.utils.ComicPageParser
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(
    val comicAPI : GetComicAPI
){
    private var disposable : Disposable? = null

    fun getComicsByUniverse(universe : String,page : Int) : MutableLiveData<ComicResponse>{
        val comicResponse = MutableLiveData<ComicResponse>()
         comicAPI.getComicsByUniverse(universe,page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ResponseBody>{
                override fun onSuccess(t: ResponseBody) {
                    comicResponse.value = ComicPageParser.parse(t)
                    disposable?.dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    disposable?.dispose()
                }
            })
        return comicResponse
    }


    fun getComicsByTag(name : String,page: Int) : MutableLiveData<ComicResponse>{
        val comicResponse = MutableLiveData<ComicResponse>()
        comicAPI.getComicsByTag(name,page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ResponseBody>{
                override fun onSuccess(t: ResponseBody) {
                    comicResponse.value = ComicPageParser.parse(t)
                    disposable?.dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    disposable?.dispose()
                }
            })
        return comicResponse
    }

    fun getPublishers() : LiveData<List<Publisher>>{
        val publisherLiveData : MutableLiveData<List<Publisher>> = MutableLiveData()

        publisherLiveData.value = listOf(
            Publisher("marvel","https://cdn.iconscout.com/icon/free/png-256/marvel-282124.png"),
            Publisher("DC","https://cdn.vox-cdn.com/thumbor/qyUvhWQpC61vjDAf7Qgb95q0WdY=/0x64:1600x1131/1200x800/filters:focal(0x64:1600x1131)/cdn.vox-cdn.com/uploads/chorus_image/image/49612017/DC_Logo_Blue_Final_573b356bd056a9.41641801.0.0.jpg"),
            Publisher("imageComics","https://thenextissuepodcast.files.wordpress.com/2018/03/image-comics-logo.jpg"),
            Publisher("2000AD","https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwja-_fRwqbnAhWXIbcAHVqmBvAQjRx6BAgBEAQ&url=https%3A%2F%2F"),
            Publisher("aftershock","https://i2.wp.com/www.comicsbeat.com/wp-content/uploads/2018/08/asc_logo_url.png?fit=1000%2C1000&ssl=1")

        )

        return publisherLiveData
    }


}