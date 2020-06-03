package non.shahad.heroesfandom.utils.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import non.shahad.heroesfandom.data.db.entities.NewsEntity

object NewsDiffCallback : DiffUtil.ItemCallback<NewsEntity>() {

    override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
        return oldItem.imgUrl == newItem.imgUrl
    }

}