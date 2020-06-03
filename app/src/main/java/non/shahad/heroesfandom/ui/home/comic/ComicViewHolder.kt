package non.shahad.heroesfandom.ui.home.comic

import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.databinding.ComicChildItemBinding
import non.shahad.heroesfandom.data.db.entities.ComicEntity

class ComicViewHolder(private val comicBinding : ComicChildItemBinding) : RecyclerView.ViewHolder(comicBinding.root) {
    fun bind(comic_Entity_ : ComicEntity){
        comicBinding.comic = comic_Entity_
    }
}