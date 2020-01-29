package non.shahad.heroesfandom.ui.movies.movies

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.databinding.MovieParentItemBinding

class MovieParentViewHolder(val binding : MovieParentItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(title : String,movies : List<MovieEntity>){
        binding.listTitle.text = title
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
            adapter = MovieAdapter(movies)
        }
    }

}