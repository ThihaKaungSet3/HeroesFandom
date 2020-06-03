package non.shahad.heroesfandom.ui.home.publisher

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.databinding.ComicTypeParentItemBinding
import non.shahad.heroesfandom.data.models.Home
import non.shahad.heroesfandom.utils.extensions.bindedView

class PublisherAdapterDelegate() : AdapterDelegate<List<Home>> (){

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PublisherParentViewHolder(bindedView(parent,R.layout.comic_type_parent_item) as ComicTypeParentItemBinding)
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