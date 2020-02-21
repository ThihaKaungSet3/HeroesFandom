package non.shahad.heroesfandom.ui.home

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.heroesfandom.ui.home.comic.ComicAdapterDelegate
import non.shahad.heroesfandom.ui.home.models.Home
import non.shahad.heroesfandom.ui.home.publisher.PublisherAdapter
import non.shahad.heroesfandom.ui.home.publisher.PublisherAdapterDelegate

class HomeAdapterDelegate(context: Context) : AsyncListDifferDelegationAdapter<Home>(HomeDiffCallback){
    init {
        delegatesManager
            .addDelegate(ComicAdapterDelegate(context))
            .addDelegate(PublisherAdapterDelegate(context))
    }
}
