package non.shahad.heroesfandom.ui.movies.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.ViewTypes
import non.shahad.heroesfandom.data.local.entities.MovieEntity
import non.shahad.heroesfandom.databinding.MovieParentItemBinding
import non.shahad.heroesfandom.ui.movies.models.MainMovies

class MovieAdapterDelegate(val context : Context) : AdapterDelegate<List<MainMovies>>(){
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding : MovieParentItemBinding = DataBindingUtil.inflate(inflater,
            R.layout.movie_parent_item,parent,false)

        return MovieParentViewHolder(binding)
    }

    override fun isForViewType(items: List<MainMovies>, position: Int): Boolean {
        return items[position].viewTypes == ViewTypes.MOVIES
    }

    override fun onBindViewHolder(
        items: List<MainMovies>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as MovieParentViewHolder).bind(items[position].title,items[position].movieList!!)
    }


}