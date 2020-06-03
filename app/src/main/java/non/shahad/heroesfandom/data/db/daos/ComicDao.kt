package non.shahad.heroesfandom.data.db.daos

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import non.shahad.heroesfandom.data.db.entities.ComicEntity

@Dao
abstract class ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(posts: List<ComicEntity>)

    @Query("DELETE FROM comic")
    abstract suspend fun deleteAll()

    @Query("DELETE FROM comic WHERE universe = :universe AND page = :page")
    abstract suspend fun clearComicsAssociatedWithUniverse(universe: String,page: Int)

    @Query("SELECT * FROM comic WHERE universe = :universe AND page = :page")
    abstract fun getComicsByUniverse(
        universe: String,
        page: Int
    ) : Flow<List<ComicEntity>>

    @Transaction
    open suspend fun insertComicsByUniverse(universe: String,page: Int,comics: List<ComicEntity>) {
        clearComicsAssociatedWithUniverse(universe,page)

        val comics = comics.mapIndexedNotNull { _, comicEntity ->
            comicEntity.universe = universe
            comicEntity.page = page

            comicEntity
        }

        insertAll(comics)
    }



}