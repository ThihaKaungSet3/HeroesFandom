package non.shahad.heroesfandom.ui.home.publisher

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.databinding.ComicTypeParentItemBinding
import non.shahad.heroesfandom.data.models.ParentPublisher

class PublisherParentViewHolder(private val binding : ComicTypeParentItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(parentPublisher: ParentPublisher){
        binding.apply {
            titleTxt.text = parentPublisher.name
            publishersRecycler.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
            publishersRecycler.adapter = PublisherAdapter(parentPublisher.list)
        }

    }
}