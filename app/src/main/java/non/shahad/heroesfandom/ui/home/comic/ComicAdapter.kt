package non.shahad.heroesfandom.ui.home.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.databinding.ComicChildItemBinding
import non.shahad.heroesfandom.data.local.entities.ComicEntity

class ComicAdapter(private val comicEntities : List<ComicEntity>) : RecyclerView.Adapter<ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ComicChildItemBinding = DataBindingUtil.inflate(inflater,
            R.layout.comic_child_item,parent,false)

        return ComicViewHolder(binding)
    }

    override fun getItemCount(): Int = comicEntities.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comic_Entity_ = comicEntities[position])
    }


}