package non.shahad.heroesfandom.ui.home.science

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.db.entities.ScienceNews
import non.shahad.heroesfandom.databinding.ScienceNewsVerticalItemBinding
import non.shahad.heroesfandom.utils.extensions.bindedView

class ScienceChildAdapter(
    private val news: List<ScienceNews>
) : RecyclerView.Adapter<ScienceChildAdapter.ScienceChildViewHolder>() {

    inner class ScienceChildViewHolder(
        private val binding: ScienceNewsVerticalItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(news: ScienceNews){
            binding.science = news
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ScienceChildViewHolder {

        return ScienceChildViewHolder(bindedView(
            parent,R.layout.science_news_vertical_item
        ) as ScienceNewsVerticalItemBinding)
    }

    override fun getItemCount(): Int  = news.size

    override fun onBindViewHolder(holder: ScienceChildViewHolder, position: Int) {
        holder.bind(news[position])
    }
}