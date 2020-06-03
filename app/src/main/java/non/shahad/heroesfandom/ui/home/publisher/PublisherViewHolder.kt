package non.shahad.heroesfandom.ui.home.publisher

import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.databinding.ComicTypeChildItemBinding
import non.shahad.heroesfandom.data.db.entities.PublisherEntity

class PublisherViewHolder(private val publisherBinding : ComicTypeChildItemBinding) : RecyclerView.ViewHolder(publisherBinding.root){
    fun bind(publisherEntity: PublisherEntity){
        publisherBinding.publisher = publisherEntity
    }
}