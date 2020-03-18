package non.shahad.heroesfandom.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import non.shahad.heroesfandom.data.local.entities.ComicEntity
import non.shahad.heroesfandom.data.local.entities.PublisherEntity

@Dao
interface ComicDao {

    @Query("DELETE FROM publisher")
    fun deleteAllPublishers()

    @Insert
    fun savePublishers(publisherEntities : List<PublisherEntity>)

    @Query("SELECT * FROM publisher")
    fun getPublishers() : List<PublisherEntity>

    @Query("SELECT * FROM comic WHERE category = :name AND page = :page")
    fun getComicsByCategory(name : String,page : Int) : List<ComicEntity>

    @Insert
    fun insertComic(comics : List<ComicEntity>)

}