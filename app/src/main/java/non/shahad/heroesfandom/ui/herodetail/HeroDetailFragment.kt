package non.shahad.heroesfandom.ui.herodetail


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.databinding.FragmentHeroDetailBinding
import non.shahad.heroesfandom.ui.MainActivity
import non.shahad.heroesfandom.utils.ColorPalette

class HeroDetailFragment : Fragment() {
    private lateinit var viewBinding : FragmentHeroDetailBinding
    private lateinit var heroParcel : HeroEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_hero_detail, container, false)
        return viewBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroParcel = arguments?.get("hero") as HeroEntity

        viewBinding.hero = heroParcel

        doColorThings()

        viewBinding.backBtn.setOnClickListener{
            findNavController().navigateUp()
        }


    }

    private fun doColorThings(){
        Glide.with(this)
            .load(heroParcel.images?.lg)
            .apply(RequestOptions.circleCropTransform())
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val default = resources.getColor(R.color.selected_color)
                    val dominentColor = ColorPalette.getDominentColor(resource!!,default)
                    viewBinding.view.setBackgroundColor(dominentColor)
                    (activity as MainActivity).changeStatusBarColor(dominentColor)
                    return false
                }

            })
            .into(viewBinding.heroImageView)
    }


}


