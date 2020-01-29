package non.shahad.heroesfandom.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import non.shahad.heroesfandom.data.remote.request.ComicByTagRequest
import non.shahad.heroesfandom.data.remote.request.ComicRequest
import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import non.shahad.heroesfandom.domain.model.Publisher
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    repository : Repository
) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    var comicLiveData : LiveData<ComicResponse>
    var publisherLiveData : LiveData<List<Publisher>>
    var comicByTag : LiveData<ComicResponse>

    private val fetchPublisher : MutableLiveData<Boolean> = MutableLiveData()
    private val comicPageLiveData : MutableLiveData<ComicRequest> = MutableLiveData()
    private val comicbyTagLiveData : MutableLiveData<ComicByTagRequest> = MutableLiveData()

    init {
        this.publisherLiveData = Transformations.switchMap(fetchPublisher){ fetch ->
            repository.getPublishers()
        }

        this.comicLiveData = Transformations.switchMap(comicPageLiveData){ request ->
            Timber.tag("tagg_").d("changes comic")
            repository.getComicsByUniverse(request.universe,request.page)
        }

        this.comicByTag = Transformations.switchMap(comicbyTagLiveData){request ->
            Timber.tag("tagg_").d("changes tag")
            repository.getComicsByTag(request.name,request.page)
        }
    }


    fun loadComic(universe : String,page : Int) {
        comicPageLiveData.postValue(ComicRequest(universe,page))
    }

    fun loadcomicByTag(name : String,page: Int){
        comicbyTagLiveData.postValue(ComicByTagRequest(name,page))
    }

    fun loadPublisher(load : Boolean){
        fetchPublisher.postValue(load)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}