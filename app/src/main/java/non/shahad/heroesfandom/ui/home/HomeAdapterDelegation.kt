package non.shahad.heroesfandom.ui.home

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.heroesfandom.ui.home.comic.ComicAdapterDelegate
import non.shahad.heroesfandom.data.models.Home
import non.shahad.heroesfandom.ui.home.gaming.GamingNewsAdapterDelegate
import non.shahad.heroesfandom.ui.home.news.NewsParentAdapterDelegate
import non.shahad.heroesfandom.ui.home.publisher.PublisherAdapterDelegate
import non.shahad.heroesfandom.ui.home.science.ScienceAdapterDelegate
import non.shahad.heroesfandom.utils.diffcallbacks.HomeDiffCallback

class HomeAdapterDelegation(context: Context) : AsyncListDifferDelegationAdapter<Home>(
    HomeDiffCallback
){

    init {
        delegatesManager
            .addDelegate(ComicAdapterDelegate(context))
            .addDelegate(PublisherAdapterDelegate())
            .addDelegate(NewsParentAdapterDelegate())
            .addDelegate(ScienceAdapterDelegate())
            .addDelegate(GamingNewsAdapterDelegate())
    }

    /**
     * Enhance performance while sorting list
     * you can use suspend tho(but buggy)
     */
    fun insertItems(coroutineScope: CoroutineScope, posts : List<Home>){
        coroutineScope.launch {

            val result = withContext(Dispatchers.Default){
                val local = mutableListOf<Home>()
                local.addAll(items)
                local.addAll(posts)
                return@withContext local
            }

            items = result
        }

    }
}
