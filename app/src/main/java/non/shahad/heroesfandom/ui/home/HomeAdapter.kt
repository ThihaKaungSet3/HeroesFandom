package non.shahad.heroesfandom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.databinding.ComicParentItemBinding
import non.shahad.heroesfandom.databinding.ComicTypeParentItemBinding
import non.shahad.heroesfandom.ui.home.comic.ComicParentViewHolder
import non.shahad.heroesfandom.ui.home.models.Home
import non.shahad.heroesfandom.ui.home.publisher.PublisherParentViewHolder

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val homeList : MutableList<Home> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return when(homeList[position].viewTypes){
            ViewTypes.COMIC ->  Constants.Adapter.COMIC_VIEWTYPE
            ViewTypes.PUBLISHER -> Constants.Adapter.PUBLISHER_VIEWTYPE
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){

            Constants.Adapter.COMIC_VIEWTYPE -> {
                val binding : ComicParentItemBinding = DataBindingUtil.inflate(inflater,R.layout.comic_parent_item,parent,false)
                ComicParentViewHolder(binding)
            }

            Constants.Adapter.PUBLISHER_VIEWTYPE -> {
                val binding : ComicTypeParentItemBinding = DataBindingUtil.inflate(inflater,R.layout.comic_type_parent_item,parent,false)
                PublisherParentViewHolder(binding)
            }

            else -> null!!
        }
    }

    override fun getItemCount(): Int = homeList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val home = homeList[position]
        when(getItemViewType(position)){
            Constants.Adapter.COMIC_VIEWTYPE -> {
                val parentComic = home.parentComic
                parentComic?.name = home.title
                (holder as ComicParentViewHolder).bind(parentComic!!)
            }

            Constants.Adapter.PUBLISHER_VIEWTYPE -> {
                val parentPublisher = home.parentPublisher
                parentPublisher?.name = home.title
                (holder as PublisherParentViewHolder).bind(parentPublisher!!)
            }
        }
    }

    fun addItems(items : Home){
        homeList.add(items)
        notifyItemInserted(homeList.size - 1)
    }

}