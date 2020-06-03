package non.shahad.heroesfandom.ui.movies.movies

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_child_item.view.*
import non.shahad.heroesfandom.data.db.entities.MovieEntity
import non.shahad.heroesfandom.databinding.MovieChildItemBinding
import timber.log.Timber

class MovieChildViewHolder(var binding : MovieChildItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(movieEntity: MovieEntity){
        Timber.tag("title_").d("${movieEntity.title}")
        binding.overlay = itemView.overlayView
        binding.movie = movieEntity
    }

}