package non.shahad.heroesfandom.ui.home.science

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.db.entities.ScienceNews
import non.shahad.heroesfandom.data.models.Home
import non.shahad.heroesfandom.databinding.SciencenewsParentItemBinding
import non.shahad.heroesfandom.utils.extensions.bindedView

class ScienceAdapterDelegate : AdapterDelegate<List<Home>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ScienceNewsParentViewHolder(
            bindedView(parent, R.layout.sciencenews_parent_item)
            as SciencenewsParentItemBinding
        )
    }

    override fun isForViewType(items: List<Home>, position: Int): Boolean {
        return items[position].viewTypes == ViewTypes.SCIENCE
    }

    override fun onBindViewHolder(
        items: List<Home>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ScienceNewsParentViewHolder)
            .bind(items[position].parentScienceNews!!.list)
    }

    class ScienceNewsParentViewHolder(private val binding: SciencenewsParentItemBinding) :
            RecyclerView.ViewHolder(binding.root){

        fun bind(sNews: List<ScienceNews>){
            binding.newsListView.also {
                it.adapter = ScienceChildAdapter(sNews)
            }
        }
    }
}