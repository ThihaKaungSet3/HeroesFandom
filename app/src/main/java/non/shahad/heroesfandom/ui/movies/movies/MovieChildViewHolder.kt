package non.shahad.heroesfandom.ui.movies.movies

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_child_item.view.*
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.databinding.MovieChildItemBinding

class MovieChildViewHolder(var binding : MovieChildItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(movieEntity: MovieEntity){
        binding.overlay = itemView.overlayView
        binding.movie = movieEntity
    }

}