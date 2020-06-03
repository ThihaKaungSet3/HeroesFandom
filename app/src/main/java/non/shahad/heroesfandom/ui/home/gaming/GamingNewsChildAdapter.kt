package non.shahad.heroesfandom.ui.home.gaming

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.db.entities.GamingNewsEntitiy
import non.shahad.heroesfandom.data.db.entities.ScienceNews
import non.shahad.heroesfandom.databinding.GamingNewsChildItemBinding
import non.shahad.heroesfandom.databinding.ScienceNewsVerticalItemBinding
import non.shahad.heroesfandom.utils.extensions.bindedView

class GamingNewsChildAdapter(
    private val news: List<GamingNewsEntitiy>
) : RecyclerView.Adapter<GamingNewsChildAdapter.GamingNewsChildViewHolder>() {

    inner class GamingNewsChildViewHolder(
        private val binding: GamingNewsChildItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(gaming: GamingNewsEntitiy){
            binding.gaming = gaming
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GamingNewsChildViewHolder {

        return GamingNewsChildViewHolder(
            bindedView(
            parent, R.layout.gaming_news_child_item
        ) as GamingNewsChildItemBinding
        )
    }

    override fun getItemCount(): Int  = news.size

    override fun onBindViewHolder(holder: GamingNewsChildViewHolder, position: Int) {
        holder.bind(news[position])
    }
}