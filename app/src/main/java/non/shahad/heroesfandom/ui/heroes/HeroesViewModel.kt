package non.shahad.heroesfandom.ui.heroes

import androidx.lifecycle.*
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.repositories.HeroesRepository
import non.shahad.heroesfandom.utils.domain.Resource
import timber.log.Timber
import javax.inject.Inject

class HeroesViewModel @Inject constructor(val heroesRepository: HeroesRepository) : ViewModel(){

    var allHeroes : LiveData<Resource<List<HeroEntity>>>
    private val getAll : MutableLiveData<Boolean> = MutableLiveData()

    var filteredHeroes : LiveData<List<HeroEntity>>
    private val searchQuery : MutableLiveData<String> = MutableLiveData()

    // Boolean is worked-around
    var pagedItemCount : MutableLiveData<Int> = MutableLiveData()

    init {
        this.filteredHeroes = Transformations.switchMap(searchQuery){
            heroesRepository.findHeroByName(it)
        }

        this.allHeroes = Transformations.switchMap(getAll){
            heroesRepository.getAllHeroes()
        }

        this.pagedItemCount.value = 0
    }

    fun fetchAllHeroes(fetchAll : Boolean) = this.getAll.postValue(fetchAll)

    fun findHeros(query : String) = this.searchQuery.postValue(query)

    override fun onCleared() {
        Timber.tag("autumnsong").d("onCleared")
        super.onCleared()
    }
}