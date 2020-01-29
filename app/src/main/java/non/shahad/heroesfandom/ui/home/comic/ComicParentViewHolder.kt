package non.shahad.heroesfandom.ui.home.comic

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.databinding.ComicParentItemBinding
import non.shahad.heroesfandom.ui.home.models.ParentComic

class ComicParentViewHolder(private val binding : ComicParentItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(parentComic: ParentComic){
        binding.apply {
            titleTxt.text = parentComic.name
            comicRecyclerView.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
            comicRecyclerView.adapter = ComicAdapter(parentComic.list)
        }

    }
}