package non.shahad.heroesfandom.ui.home.comic

import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.databinding.ComicChildItemBinding
import non.shahad.heroesfandom.domain.model.Comic

class ComicViewHolder(private val comicBinding : ComicChildItemBinding) : RecyclerView.ViewHolder(comicBinding.root) {
    fun bind(comic_ : Comic){
        comicBinding.comic = comic_
    }
}