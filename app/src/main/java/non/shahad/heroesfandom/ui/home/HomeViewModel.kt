package non.shahad.heroesfandom.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import non.shahad.heroesfandom.data.remote.request.ComicRequest
import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    repository : Repository
) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    var comicLiveData : LiveData<ComicResponse>
    private val comicPageLiveData : MutableLiveData<ComicRequest> = MutableLiveData()

    init {
        this.comicLiveData = Transformations.switchMap(comicPageLiveData){ request ->
            repository.getComicsByUniverse(request.universe,request.page)
        }
    }


    fun loadComic(universe : String,page : Int) {
        comicPageLiveData.postValue(ComicRequest(universe,page))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}