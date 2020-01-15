package non.shahad.heroesfandom.utils
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette


object ColorPalette{
    fun getDominentColor(drawable: Drawable,default : Int) : Int{
        val bitmap = drawable as BitmapDrawable
        val palette = Palette.from(bitmap.bitmap).generate()

        return palette.getDarkVibrantColor(default)!!
    }

}