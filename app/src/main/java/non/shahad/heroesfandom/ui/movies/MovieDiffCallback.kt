package non.shahad.heroesfandom.ui.movies

import androidx.recyclerview.widget.DiffUtil
import non.shahad.heroesfandom.ui.movies.models.MainMovies

object MovieDiffCallback : DiffUtil.ItemCallback<MainMovies>(){
    override fun areItemsTheSame(oldItem: MainMovies, newItem: MainMovies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MainMovies, newItem: MainMovies): Boolean {
        return oldItem.title == newItem.title
    }

}