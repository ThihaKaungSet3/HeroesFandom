package non.shahad.heroesfandom.ui.movies.banner

import com.smarteist.autoimageslider.SliderViewAdapter
import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity
import non.shahad.heroesfandom.databinding.BannerChildItemBinding

class BannerChildViewHolder(val binding : BannerChildItemBinding) : SliderViewAdapter.ViewHolder(binding.root){
    fun bind(bannerItem : MovieNewsEntity){
        binding.news = bannerItem
    }
}