package non.shahad.heroesfandom.ui.home.news

import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.db.entities.NewsEntity
import non.shahad.heroesfandom.databinding.NewsChildItemBinding
import non.shahad.heroesfandom.utils.extensions.bindedView

class NewsChildAdapter(
    private val news: List<NewsEntity>
) : SliderViewAdapter<NewsChildAdapter.NewsChildViewHolder>() {

    inner class NewsChildViewHolder(private val binding: NewsChildItemBinding) : SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(_news: NewsEntity){
            binding.news = _news
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): NewsChildViewHolder {
        return NewsChildViewHolder(bindedView(parent!!,R.layout.news_child_item) as NewsChildItemBinding)
    }

    override fun getCount(): Int = news.size

    override fun onBindViewHolder(viewHolder: NewsChildViewHolder?, position: Int) {
        viewHolder?.bind(_news = news[position])
    }
}