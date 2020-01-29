package non.shahad.heroesfandom.ui.movies.banner

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import non.shahad.heroesfandom.core.PosterPath
import non.shahad.heroesfandom.databinding.BannerChildItemBinding
import non.shahad.heroesfandom.ui.movies.models.Banner

class BannerChildViewHolder(val binding : BannerChildItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(bannerItem : Banner){
        Glide.with(binding.root.context)
            .load("${PosterPath.getPosterPath(bannerItem.posterUrl)}")
            .into(binding.bannerImg)
    }
}