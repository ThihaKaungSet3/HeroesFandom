package non.shahad.heroesfandom.ui.home

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import non.shahad.heroesfandom.repositories.HomeRepository
import non.shahad.heroesfandom.ui.home.models.Home
import non.shahad.heroesfandom.utils.domain.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository : HomeRepository
) : ViewModel(){

    fun getHomeData() : LiveData<Resource<List<Home>>>{
        return homeRepository.getHomeData()
    }

    override fun onCleared() {
        super.onCleared()
    }
}