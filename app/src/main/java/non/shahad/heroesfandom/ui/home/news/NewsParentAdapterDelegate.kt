package non.shahad.heroesfandom.ui.home.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.db.entities.NewsEntity
import non.shahad.heroesfandom.data.models.Home
import non.shahad.heroesfandom.databinding.NewsParentItemBinding
import non.shahad.heroesfandom.utils.extensions.bindedView

class NewsParentAdapterDelegate : AdapterDelegate<List<Home>>(){

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsParentViewHolder(bindedView(parent, R.layout.news_parent_item) as NewsParentItemBinding)
    }

    override fun isForViewType(items: List<Home>, position: Int): Boolean {
        return items[position].viewTypes == ViewTypes.NEWS
    }

    override fun onBindViewHolder(
        items: List<Home>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as NewsParentViewHolder).bind(items[position].parentNews?.list!!)
    }

    inner class NewsParentViewHolder(private val binding: NewsParentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsList: List<NewsEntity>){
            binding.newsSlider.setSliderAdapter(NewsChildAdapter(newsList))
        }
    }

}