package non.shahad.heroesfandom.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movies_listitem.view.*
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.databinding.MoviesListitemBinding
import timber.log.Timber
import java.util.ArrayList

class MoviesAdapter(
    var movieList : ArrayList<MovieEntity>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    class MovieViewHolder(val itemBinding : MoviesListitemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(movie : MovieEntity){
//            Timber.tag("song").d("${movie}")
            itemBinding.overlay = itemView.overlay_view
            itemBinding.movie = movie

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding : MoviesListitemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movies_listitem,
            parent,
            false)

        return MovieViewHolder(binding)
    }

    fun setMovieList(newmovieList : List<MovieEntity>){
        val diffResult = DiffUtil.calculateDiff(MoviesDiffCallback(movieList,newmovieList))
        movieList.clear()
        movieList.addAll(newmovieList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addMovies(movies : List<MovieEntity>){
        movieList.addAll(movies)
        notifyItemRangeChanged(movieList.size - movies.size + 1 , movies.size)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

}