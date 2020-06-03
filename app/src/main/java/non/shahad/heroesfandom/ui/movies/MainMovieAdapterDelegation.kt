package non.shahad.heroesfandom.ui.movies

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.heroesfandom.ui.movies.banner.BannerAdapterDelegate
import non.shahad.heroesfandom.data.models.MainMovies
import non.shahad.heroesfandom.ui.movies.movies.MovieAdapterDelegate
import non.shahad.heroesfandom.utils.diffcallbacks.MovieDiffCallback

class MainMovieAdapterDelegation(context: Context)  : AsyncListDifferDelegationAdapter<MainMovies>(
    MovieDiffCallback
){

    init {
        delegatesManager.addDelegate(MovieAdapterDelegate(context))
        delegatesManager.addDelegate(BannerAdapterDelegate(context))
    }

}