package non.shahad.heroesfandom.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.get
import kotlinx.coroutines.*
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.models.*
import non.shahad.heroesfandom.data.remote.responses.HomeDataResponse
import non.shahad.heroesfandom.utils.extensions.timber
import javax.inject.Inject
import javax.inject.Named

class HomeViewModel @Inject constructor(
    @Named("homeDataStore")
    private val homeStore: Store<Int,HomeDataResponse>
) : ViewModel(){

    var isLoading : Boolean = false

    var isLast: Boolean = false

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        timber("HomeViewModel_","${throwable.message}")
    }

    val homeResponse = MutableLiveData<List<Home>>()

    fun fetchHomeData(page: Int) {
        isLoading = true
        viewModelScope.launch (exceptionHandler){
            withContext(Dispatchers.IO) {
                val homeList = mutableListOf<Home>()
                val result = homeStore.get(page)

                isLast = result.last

                // News
                result.heroesNews?.also {
                    homeList.add(
                        Home(
                            id = 1,
                            title = "Latest News",
                            viewTypes = ViewTypes.NEWS,
                            parentNews = ParentNews(list = it),
                            parentComic = null
                        )
                    )
                }

                // Marvel
                result.marvelComics?.also {
                    homeList.add(
                        Home(id = 2,
                            viewTypes = ViewTypes.COMIC,
                            parentComic = ParentComic("Marvel Comic", it),
                            title = "Marvel Comic")
                    )
                }

                // DC
                result.dcComics?.also {
                    homeList.add(
                        Home(id = 3,
                            viewTypes = ViewTypes.COMIC,
                            parentComic = ParentComic("DC Comic", it),
                            title = "DC Comic")
                    )
                }

                result.scienceNews?.also {
                    homeList.add(
                        Home(id = 4,
                            viewTypes = ViewTypes.SCIENCE,
                            parentScienceNews = ParentScienceNews("Science News", it),
                            title = "Science News")
                    )
                }

                result.gamingNews?.also {
                    homeList.add(
                        Home(id = 5,
                            viewTypes = ViewTypes.GAMING,
                            parentGamingNews = ParentGamingNews("Gaming News", it),
                            title = "Gaming News")
                    )
                }

                timber("HoemVi","${result.scienceNews}")
                homeResponse.postValue(homeList)
                isLoading = false
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}