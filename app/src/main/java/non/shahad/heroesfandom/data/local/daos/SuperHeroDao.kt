package non.shahad.heroesfandom.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import non.shahad.heroesfandom.data.local.entities.HeroEntity

@Dao
interface SuperHeroDao{

    @Query("SELECT * FROM hero")
    fun getAllHeroes() : LiveData<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(heroEntity: HeroEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllheroes(heroesList : List<HeroEntity>)

    @Query("DELETE FROM hero")
    fun deleteAllHeroes()

    @Query("SELECT count(*) FROM hero")
    fun getHeroesCount() : Int

    @Transaction
    fun deleteAndInsertAll(heroesList: List<HeroEntity>){
        deleteAllHeroes()
        insertAllheroes(heroesList)
    }

}