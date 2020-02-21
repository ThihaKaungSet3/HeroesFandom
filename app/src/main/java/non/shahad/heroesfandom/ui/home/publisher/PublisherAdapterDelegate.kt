package non.shahad.heroesfandom.ui.home.publisher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.databinding.ComicParentItemBinding
import non.shahad.heroesfandom.databinding.ComicTypeParentItemBinding
import non.shahad.heroesfandom.ui.home.comic.ComicParentViewHolder
import non.shahad.heroesfandom.ui.home.models.Home

class PublisherAdapterDelegate(context : Context) : AdapterDelegate<List<Home>> (){

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding : ComicTypeParentItemBinding = DataBindingUtil.inflate(inflater,
            R.layout.comic_type_parent_item,parent,false)

        return PublisherParentViewHolder(binding)
    }

    override fun isForViewType(items: List<Home>, position: Int): Boolean {
        return items[position].viewTypes == ViewTypes.PUBLISHER
    }

    override fun onBindViewHolder(
        items: List<Home>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as PublisherParentViewHolder).bind(items[position].parentPublisher!!)
    }
}