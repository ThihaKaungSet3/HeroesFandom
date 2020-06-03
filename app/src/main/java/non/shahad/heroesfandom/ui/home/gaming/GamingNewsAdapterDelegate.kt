package non.shahad.heroesfandom.ui.home.gaming

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.db.entities.GamingNewsEntitiy
import non.shahad.heroesfandom.data.db.entities.ScienceNews
import non.shahad.heroesfandom.data.models.Home
import non.shahad.heroesfandom.databinding.GamingNewsParentItemBinding
import non.shahad.heroesfandom.databinding.SciencenewsParentItemBinding
import non.shahad.heroesfandom.ui.home.science.ScienceChildAdapter
import non.shahad.heroesfandom.utils.extensions.bindedView

class GamingNewsAdapterDelegate : AdapterDelegate<List<Home>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return GamingNewsParentViewHolder(
            bindedView(parent, R.layout.gaming_news_parent_item)
                    as GamingNewsParentItemBinding
        )
    }

    override fun isForViewType(items: List<Home>, position: Int): Boolean {
        return items[position].viewTypes == ViewTypes.GAMING
    }

    override fun onBindViewHolder(
        items: List<Home>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as GamingNewsParentViewHolder)
            .bind(items[position].parentGamingNews!!.list)
    }

    class GamingNewsParentViewHolder(private val binding: GamingNewsParentItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(sNews: List<GamingNewsEntitiy>){
            binding.gamingNewsRV.also {
                it.adapter = GamingNewsChildAdapter(sNews)
            }
        }
    }
}