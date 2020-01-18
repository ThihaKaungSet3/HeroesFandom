package non.shahad.heroesfandom.utils.custom

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSnapHelper : LinearSnapHelper(){

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val centerView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION

        val position = layoutManager?.getPosition(centerView)
        var targetPosition : Int = -1

        if (layoutManager!!.canScrollVertically()){
            if (velocityX < 0){
                targetPosition = position!! - 1
            }else{
                targetPosition = position!! + 1
            }
        }

        val firstItem = 0
        val lastItem = layoutManager.itemCount - 1

        return lastItem.coerceAtMost(targetPosition.coerceAtLeast(firstItem))
    }

}