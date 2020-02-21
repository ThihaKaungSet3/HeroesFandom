package non.shahad.heroesfandom.ui.home

import androidx.recyclerview.widget.DiffUtil
import non.shahad.heroesfandom.ui.home.models.Home

object HomeDiffCallback : DiffUtil.ItemCallback<Home>() {
    override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem.viewTypes == newItem.viewTypes
    }
}