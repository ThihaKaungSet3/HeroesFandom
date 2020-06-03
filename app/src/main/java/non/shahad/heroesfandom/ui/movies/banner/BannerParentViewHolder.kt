package non.shahad.heroesfandom.ui.movies.banner

import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity
import non.shahad.heroesfandom.databinding.BannerParentItemBinding
import non.shahad.heroesfandom.data.models.Banner

class BannerParentViewHolder(val binding: BannerParentItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(itemList : List<MovieNewsEntity>){

        with(binding.slider){
            setSliderAdapter(BannerChildAdapter(itemList))
        }

    }
}