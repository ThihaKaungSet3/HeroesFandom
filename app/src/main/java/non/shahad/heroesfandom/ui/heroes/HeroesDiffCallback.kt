package non.shahad.heroesfandom.ui.heroes

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import non.shahad.heroesfandom.data.local.entities.HeroEntity

class HeroesDiffCallback(
    private val oldList : List<HeroEntity>,
    private val newList : List<HeroEntity>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size




}