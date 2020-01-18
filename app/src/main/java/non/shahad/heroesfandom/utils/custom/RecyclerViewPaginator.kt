package non.shahad.heroesfandom.utils.custom

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import timber.log.Timber

 abstract class RecyclerViewPaginator(
     private val recyclerView : RecyclerView) : RecyclerView.OnScrollListener(){

    /** expected item */
    private var threshold = 1
    /** current page */
    private var currentPage = 1

    var endWithAuto = false

    init {
        recyclerView.addOnScrollListener(this)
    }

     override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
         super.onScrollStateChanged(recyclerView, newState)
         if (newState == RecyclerView.SCROLL_STATE_IDLE){
             val layoutManager = recyclerView.layoutManager

             layoutManager?.let {
                 val visibleItemCount = it.itemCount
                 val totalItemCount = it.childCount

                 val firstVisibleItemPosition = when(layoutManager){
                     is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                     is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                     else -> return
                 }

                 if (isLastPage() || isLoading()) {
                     Timber.tag("scrollsong").d("last or loading : $currentPage")
                     return
                 }


                 if ((visibleItemCount + firstVisibleItemPosition + threshold) >= totalItemCount){
                     Timber.tag("scrollsong").d("Here: $currentPage")
                     if (!endWithAuto){
                         endWithAuto = true
                         Timber.tag("scrollsong").d("loadmore $currentPage")
                         loadMore(++currentPage)
                     } else endWithAuto = false
                 }else{
                     Timber.tag("scrollsong").d("end with auto 2")
                     endWithAuto = false
                 }
             }
         }
     }



    fun resetCurrentPage(){
        this.currentPage = 0
    }

    abstract fun isLastPage() : Boolean
    abstract fun loadMore(page : Int)
    abstract fun isLoading() : Boolean

}
