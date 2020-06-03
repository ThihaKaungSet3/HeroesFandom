package non.shahad.heroesfandom.data.db.daos

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import non.shahad.heroesfandom.data.db.entities.MovieEntity
import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity

@Dao
abstract class MovieNewsDao {
    @Query("DELETE FROM movie_news")
    abstract suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMoviesList(movieNewsList: List<MovieNewsEntity>)

    @Query("SELECT * FROM movie_news WHERE page = :page")
    abstract fun getMoviesNewsByPage(page: Int) : Flow<List<MovieNewsEntity>>

    @Query("DELETE FROM movie_news WHERE page = :page")
    abstract suspend fun clearNewsAssociatedWithPage(page: Int)

    @Transaction
    open suspend fun insertMovieByCategory(page: Int,movies: List<MovieNewsEntity>) {
        clearNewsAssociatedWithPage(page)

        val comics = movies.mapIndexedNotNull { _, entity ->
            entity.page = page

            entity
        }

        insertMoviesList(comics)
    }
}