package non.shahad.heroesfandom.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import non.shahad.heroesfandom.data.remote.request.ComicByTagRequest
import non.shahad.heroesfandom.data.remote.request.ComicRequest
import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import non.shahad.heroesfandom.data.local.entities.PublisherEntity
import non.shahad.heroesfandom.repositories.HomeRepository
import non.shahad.heroesfandom.ui.home.models.Home
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val homeRepository : HomeRepository
) : ViewModel(){

    val memoryCache = mutableListOf<Home>()
    private val compositeDisposable = CompositeDisposable()
    var comicLiveData : LiveData<Home>
    var publisherEntityLiveData : LiveData<List<PublisherEntity>>
//    var comicByTag : LiveData<ComicResponse>

    private val fetchPublisher : MutableLiveData<Boolean> = MutableLiveData()
    private val comicPageLiveData : MutableLiveData<ComicRequest> = MutableLiveData()
    private val comicbyTagLiveData : MutableLiveData<ComicByTagRequest> = MutableLiveData()

    init {
        this.publisherEntityLiveData = Transformations.switchMap(fetchPublisher){ fetch ->
            homeRepository.getPublisherS()
        }

        this.comicLiveData = Transformations.switchMap(comicPageLiveData){ request ->
            homeRepository.getMarvelComics(request.page)
        }
//
//        this.comicByTag = Transformations.switchMap(comicbyTagLiveData){request ->
//            homeRepository.getComicsByTag(request.name,request.page)
//        }


    }

    fun addComic(comic: Home){
        memoryCache.add(comic)
    }

    fun addComicList(list: List<Home>){
        memoryCache.addAll(list)
    }

    fun clearList(){
        memoryCache.clear()
    }

    fun isAlreadyContain(list: List<Home>) : Boolean{
        memoryCache.forEach{cache ->
            list.forEach{ movie ->
                if (movie.id == cache.id){
                    return true
                }
            }
        }
        return false
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