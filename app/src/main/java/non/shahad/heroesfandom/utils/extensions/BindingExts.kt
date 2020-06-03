package non.shahad.heroesfandom.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun bindedView(
    container: ViewGroup,
    layoutRes: Int
) : ViewDataBinding {
    return DataBindingUtil.inflate(inflater(container),layoutRes,container,false)
}

fun inflater(viewGroup: ViewGroup) : LayoutInflater {
    return LayoutInflater.from(viewGroup.context)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}