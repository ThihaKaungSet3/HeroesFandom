package non.shahad.heroesfandom.ui.movies.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.databinding.BannerChildItemBinding
import non.shahad.heroesfandom.ui.movies.models.Banner

class BannerChildAdapter(private val bannerItems : List<Banner>) : RecyclerView.Adapter<BannerChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : BannerChildItemBinding = DataBindingUtil.inflate(inflater,
            R.layout.banner_child_item,parent,false)
        return BannerChildViewHolder(binding)
    }

    override fun getItemCount(): Int = bannerItems.size

    override fun onBindViewHolder(holderChild: BannerChildViewHolder, position: Int) {
        holderChild.bind(bannerItems[position])
    }
}