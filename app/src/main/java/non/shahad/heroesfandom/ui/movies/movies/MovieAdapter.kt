package non.shahad.heroesfandom.ui.movies.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.databinding.MovieChildItemBinding

class MovieAdapter(val movieList : List<MovieEntity>) : RecyclerView.Adapter<MovieChildViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : MovieChildItemBinding = DataBindingUtil.inflate(inflater,R.layout.movie_child_item,parent,false)
        return MovieChildViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieChildViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

}