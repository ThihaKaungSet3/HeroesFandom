package non.shahad.heroesfandom.utils.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import non.shahad.heroesfandom.data.models.MainMovies

object MovieDiffCallback : DiffUtil.ItemCallback<MainMovies>() {
    override fun areItemsTheSame(oldItem: MainMovies, newItem: MainMovies): Boolean {
        return oldItem.viewTypes == newItem.viewTypes
    }

    override fun areContentsTheSame(oldItem: MainMovies, newItem: MainMovies): Boolean {
        return oldItem.title == newItem.title && oldItem.id == newItem.id
    }
}