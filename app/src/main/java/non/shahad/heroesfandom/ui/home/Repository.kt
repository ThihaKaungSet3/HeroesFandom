package non.shahad.heroesfandom.ui.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import non.shahad.heroesfandom.data.remote.GetComicAPI
import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import non.shahad.heroesfandom.domain.model.Comic
import okhttp3.ResponseBody
import org.jsoup.Jsoup
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
                    val document = Jsoup.parse(t.string())
                    val articles = document.getElementsByTag("article")
                    val totalPage = document.select("a.page-numbers").last().text()
                    val list : ArrayList<Comic> = arrayListOf()


                    articles.forEach {
                        val postTitle = it.getElementsByClass("post-title")[0].text()
                        val poster = it.getElementsByClass("post-header-image")[0].getElementsByTag("img").attr("src")
                        val category = it.getElementsByClass("post-category").text()
                        val postLink = it.getElementsByClass("post-header-image").select("a").attr("href")
                        val publishedDate = it.getElementsByTag("time").text()


                            list.add(
                            Comic(
                                title = postTitle,
                                posterUrl = poster,
                                category = category,
                                postLink = postLink,
                                publishedDate = publishedDate
                                )
                        )
                    }
                    comicResponse.value = ComicResponse(page = totalPage.toInt(),list = list)
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


}