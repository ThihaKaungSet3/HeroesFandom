package non.shahad.heroesfandom.ui.bindings

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import non.shahad.heroesfandom.core.PosterPath
import non.shahad.heroesfandom.utils.extensions.visible

@BindingAdapter("bindingHeroesImage")
fun bindingHeroesImage(imageView: ImageView,url : String?){
    url.let {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }
}

@BindingAdapter("bindingMoviePoster","overlay")
fun bindingMoviePoster(imageView: ImageView,url: String?,overlay : View){
    url.let {
        Glide.with(imageView.context)
            .load(PosterPath.getBackdropPath(it))
            .listener(GlidePalette.with(PosterPath.getBackdropPath(it))
                .use(BitmapPalette.Profile.VIBRANT_DARK)
                .intoBackground(overlay)
                .crossfade(true))
            .into(imageView)
    }
}

@BindingAdapter("bindNewsImage")
fun bindMovieImage(imageView: ImageView,url: String?){
    url?.also {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}

@BindingAdapter("bindMovieNewsStatus")
fun bindMovieNewsStatus(textView: TextView,status: String){
    if (status != "None") {
        with(textView){
            visible()
            text = status
        }
    }
}

@BindingAdapter("bindAuthor")
fun bindAuthor(textView: TextView,author: String){
    textView.text = "By ${author}"
}

@BindingAdapter("bindingComicPoster")
fun bindingComicPoster(imageView: ImageView,url : String?){
    url.let {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}