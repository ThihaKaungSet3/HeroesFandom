package non.shahad.heroesfandom.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.Constants
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.databinding.BannerParentItemBinding
import non.shahad.heroesfandom.databinding.MovieParentItemBinding
import non.shahad.heroesfandom.ui.movies.banner.BannerParentViewHolder
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import non.shahad.heroesfandom.ui.movies.movies.MovieParentViewHolder
import java.util.ArrayList

class MainMoviesAdapter(
    var movieList : MutableList<MainMovies>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemViewType(position: Int): Int {
        val model = movieList[position]
        return if(model.viewTypes == ViewTypes.BANNER){
            Constants.Adapter.BANNER_VIEWTYPE
        }else {
            Constants.Adapter.MOVIE_VIEWTYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType){
            Constants.Adapter.BANNER_VIEWTYPE -> {
                val bBinding : BannerParentItemBinding = DataBindingUtil.inflate(inflater,R.layout.banner_parent_item,parent,false)
                BannerParentViewHolder(bBinding)
            }
            Constants.Adapter.MOVIE_VIEWTYPE ->{
                val mBinding : MovieParentItemBinding = DataBindingUtil.inflate(inflater,R.layout.movie_parent_item,parent,false)
                MovieParentViewHolder(mBinding)
            }

            else -> null!!
        }
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            Constants.Adapter.BANNER_VIEWTYPE -> {
                (holder as BannerParentViewHolder).bind(movieList[position].bannerItemList!!)
            }
            Constants.Adapter.MOVIE_VIEWTYPE ->{
                (holder as MovieParentViewHolder).bind(movieList[position].title,movieList[position].movieList!!)
            }
        }
    }

    fun addItem(mainMovies: MainMovies){
        this.movieList.add(mainMovies)
        notifyDataSetChanged()
//        notifyItemInserted(movieList.size - 1)
    }


}