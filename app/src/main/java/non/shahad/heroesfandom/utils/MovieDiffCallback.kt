package non.shahad.heroesfandom.utils

import androidx.recyclerview.widget.DiffUtil
import non.shahad.heroesfandom.ui.movies.models.MainMovies

object MovieDiffCallback : DiffUtil.ItemCallback<MainMovies>() {
    override fun areItemsTheSame(oldItem: MainMovies, newItem: MainMovies): Boolean {
        return oldItem.viewTypes == newItem.viewTypes
    }

    override fun areContentsTheSame(oldItem: MainMovies, newItem: MainMovies): Boolean {
        return oldItem.title == newItem.title && oldItem.id == newItem.id
    }
}