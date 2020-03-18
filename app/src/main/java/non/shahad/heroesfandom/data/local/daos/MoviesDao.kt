package non.shahad.heroesfandom.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.data.local.entities.MovieEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE page = :page")
    fun getAllMovies(page : Int) : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id : Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM movie WHERE category = :name AND page = :page ")
    fun getMoviesByCategoryName(name : String,page : Int) : List<MovieEntity>

    @Query("SELECT * FROM movie WHERE category = '${Constants.NetworkService.DISCOVER}'")
    fun getTrendingMovies() : List<MovieEntity>

    @Query("DELETE FROM movie WHERE category = :name AND page = :page")
    fun deleteSpecificMovies(name : String,page: Int)


}