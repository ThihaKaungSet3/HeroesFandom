package non.shahad.heroesfandom.ui.movies

import androidx.recyclerview.widget.DiffUtil
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.data.local.entities.MovieEntity

class MoviesDiffCallback(
    private val oldList : List<MovieEntity>,
    private val newList : List<MovieEntity>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].original_title == newList[newItemPosition].original_title
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

}