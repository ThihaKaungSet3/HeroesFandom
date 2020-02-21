package non.shahad.heroesfandom.ui.movies

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.heroesfandom.ui.movies.banner.BannerAdapterDelegate
import non.shahad.heroesfandom.ui.movies.models.MainMovies
import non.shahad.heroesfandom.ui.movies.movies.MovieAdapterDelegate

class MainMovieDelegateAdapter(context: Context)  : AsyncListDifferDelegationAdapter<MainMovies>(MovieDiffCallback){

    init {
        delegatesManager
            .addDelegate(MovieAdapterDelegate(context))
            .addDelegate(BannerAdapterDelegate(context))
    }


}