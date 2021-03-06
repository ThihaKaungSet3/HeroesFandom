package non.shahad.heroesfandom.ui.herodetail


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.db.entities.HeroEntity
import non.shahad.heroesfandom.databinding.FragmentHeroDetailBinding
import non.shahad.heroesfandom.ui.MainActivity
import non.shahad.heroesfandom.utils.ColorPalette
import timber.log.Timber

class HeroDetailFragment : Fragment() {
    private lateinit var viewBinding : FragmentHeroDetailBinding
    private lateinit var heroParcel : HeroEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_hero_detail, container, false)
        return viewBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroParcel = arguments?.get("hero") as HeroEntity

        viewBinding.hero = heroParcel
        toolbarThings()
        doColorThings()

    }

    private fun toolbarThings(){
        viewBinding.toolbar.setNavigationOnClickListener {
            (context as MainActivity).navController.popFragment()
            Timber.tag("OhBaby").d("${(context as MainActivity).navController}")
//            findNavController().navigateUp()
        }

        viewBinding.toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.saved -> {
                    Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }
    }


    // TODO delete palette API and use glidepalette
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

                    val default = resources.getColor(R.color.colorPrimaryDark)
                    val dominentColor = ColorPalette.getDominentColor(resource!!,default)
                    viewBinding.heroesAppbar.setBackgroundColor(dominentColor)
                    viewBinding.heroesCollapsing.contentScrim = dominentColor.toDrawable()
                    return false

                }

            })
            .into(viewBinding.heroesImageView)
    }

    companion object{
        fun newInstance() : HeroDetailFragment {
            return HeroDetailFragment()
        }
    }

    private fun changeProgressbarColor(color : Int){
        // TODO
//        viewBinding.apply {
//            strengthProgress.isIndeterminate = true

//            intelligenceProgress.progressDrawable = drawable
//            speedProgress.progressDrawable = drawable
//            durabilityProgress.progressDrawable = drawable
//            powerProgress.progressDrawable = drawable
//            combatProgress.progressDrawable = drawable
//        }
    }




}


