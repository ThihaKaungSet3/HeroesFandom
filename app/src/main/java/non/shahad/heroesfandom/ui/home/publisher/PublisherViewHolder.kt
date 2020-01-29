package non.shahad.heroesfandom.ui.home.publisher

import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.databinding.ComicTypeChildItemBinding
import non.shahad.heroesfandom.databinding.ComicTypeParentItemBinding
import non.shahad.heroesfandom.domain.model.Publisher

class PublisherViewHolder(private val publisherBinding : ComicTypeChildItemBinding) : RecyclerView.ViewHolder(publisherBinding.root){
    fun bind(publisher: Publisher){
        publisherBinding.publisher = publisher
    }
}