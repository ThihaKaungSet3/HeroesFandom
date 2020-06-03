package non.shahad.heroesfandom.ui.movies.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.db.entities.MovieNewsEntity
import non.shahad.heroesfandom.databinding.BannerChildItemBinding
import non.shahad.heroesfandom.data.models.Banner
import non.shahad.heroesfandom.utils.extensions.bindedView

class BannerChildAdapter(private val bannerItems : List<MovieNewsEntity>) : SliderViewAdapter<BannerChildViewHolder>() {

    override fun onBindViewHolder(holderChild: BannerChildViewHolder, position: Int) {
        holderChild.bind(bannerItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?): BannerChildViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return BannerChildViewHolder(bindedView(parent!!,R.layout.banner_child_item) as BannerChildItemBinding)
    }

    override fun getCount(): Int = bannerItems.size
}