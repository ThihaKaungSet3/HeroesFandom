package non.shahad.heroesfandom.ui.home.publisher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.databinding.ComicTypeChildItemBinding
import non.shahad.heroesfandom.data.db.entities.PublisherEntity

class PublisherAdapter(private val publisherEntities : List<PublisherEntity>) : RecyclerView.Adapter<PublisherViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ComicTypeChildItemBinding = DataBindingUtil.inflate(inflater,R.layout.comic_type_child_item,parent,false)

        return PublisherViewHolder(binding)
    }

    override fun getItemCount(): Int = publisherEntities.size

    override fun onBindViewHolder(holder: PublisherViewHolder, position: Int) {
        holder.bind(publisherEntity = publisherEntities[position])
    }

}