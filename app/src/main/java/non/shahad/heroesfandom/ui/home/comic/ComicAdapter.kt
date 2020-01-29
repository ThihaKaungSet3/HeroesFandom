package non.shahad.heroesfandom.ui.home.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.databinding.ComicChildItemBinding
import non.shahad.heroesfandom.databinding.ComicTypeChildItemBinding
import non.shahad.heroesfandom.domain.model.Comic
import non.shahad.heroesfandom.ui.home.publisher.PublisherViewHolder

class ComicAdapter(private val comics : List<Comic>) : RecyclerView.Adapter<ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ComicChildItemBinding = DataBindingUtil.inflate(inflater,
            R.layout.comic_child_item,parent,false)

        return ComicViewHolder(binding)
    }

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comic_ = comics[position])
    }


}