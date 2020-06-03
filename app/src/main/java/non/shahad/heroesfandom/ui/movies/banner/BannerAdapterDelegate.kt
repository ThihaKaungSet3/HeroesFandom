package non.shahad.heroesfandom.ui.movies.banner

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.databinding.BannerParentItemBinding
import non.shahad.heroesfandom.data.models.MainMovies

class BannerAdapterDelegate(val context : Context) : AdapterDelegate<List<MainMovies>>(){
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding : BannerParentItemBinding = DataBindingUtil.inflate(inflater,R.layout.banner_parent_item,parent,false)

        return BannerParentViewHolder(binding)
    }

    override fun isForViewType(items: List<MainMovies>, position: Int): Boolean {
        return position == 0 || items[position].viewTypes == ViewTypes.NEWS
    }

    override fun onBindViewHolder(
        items: List<MainMovies>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as BannerParentViewHolder).bind(items[position].newsItemList!!)
    }


}