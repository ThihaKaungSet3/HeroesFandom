package non.shahad.heroesfandom.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import non.shahad.heroesfandom.data.db.entities.HeroEntity

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

    @Query("SELECT * FROM hero WHERE name LIKE '%' || :name || '%'")
    fun findHeroByName(name : String) : LiveData<List<HeroEntity>>

    @Transaction
    fun deleteAndInsertAll(heroesList: List<HeroEntity>){
        deleteAllHeroes()
        insertAllheroes(heroesList)
    }

}