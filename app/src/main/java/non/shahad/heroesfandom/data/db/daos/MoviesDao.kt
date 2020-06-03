package non.shahad.heroesfandom.data.db.daos

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import non.shahad.heroesfandom.data.db.entities.MovieEntity

@Dao
abstract class MoviesDao {

    @Query("DELETE FROM movie")
    abstract suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMoviesList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE category = :category AND page = :page")
    abstract fun getMoviesByCategory(category: String,page: Int) : Flow<List<MovieEntity>>

    @Query("DELETE FROM movie WHERE category = :category AND page = :page")
    abstract suspend fun clearMoviesAssociatedWithCategory(category: String,page: Int)

    @Transaction
    open suspend fun insertMovieByCategory(category: String,page: Int,movies: List<MovieEntity>) {
        clearMoviesAssociatedWithCategory(category,page)

        val comics = movies.mapIndexedNotNull { _, entity ->
            entity.category = category
            entity.page = page

            entity
        }

        insertMoviesList(comics)
    }


}