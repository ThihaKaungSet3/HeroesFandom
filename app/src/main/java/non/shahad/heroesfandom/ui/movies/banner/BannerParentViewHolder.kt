package non.shahad.heroesfandom.ui.movies.banner

import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.databinding.BannerParentItemBinding
import non.shahad.heroesfandom.ui.movies.models.Banner
import non.shahad.heroesfandom.utils.custom.BannerLayoutManager

class BannerParentViewHolder(val binding: BannerParentItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(itemList : List<Banner>){
        binding.movieRecycler.apply {
            layoutManager = BannerLayoutManager(binding.root.context,this,5)
            adapter = BannerAdapter(itemList)

            with(binding.interceptLayout){
                setIntercept(false)
            }
        }
    }
}