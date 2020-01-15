package non.shahad.heroesfandom.ui.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.repositories.HeroesRepository
import non.shahad.heroesfandom.utils.domain.Resource
import javax.inject.Inject

class HeroesViewModel @Inject constructor(val heroesRepository: HeroesRepository) : ViewModel(){

    fun getAllHeroes() : LiveData<Resource<List<HeroEntity>>> = heroesRepository.getAllHeroes()

}