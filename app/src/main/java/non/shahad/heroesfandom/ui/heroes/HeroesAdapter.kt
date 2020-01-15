package non.shahad.heroesfandom.ui.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.local.entities.HeroEntity
import non.shahad.heroesfandom.databinding.HeroesListitemBinding

class HeroesAdapter(
    private var heroList: ArrayList<HeroEntity>,
    private val heroSelectListener: HeroSelectListener) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>(){

    /**
     * Passed imageview along with callback so
     * I need to use as sharedElement for fragment transition
     */
    class HeroesViewHolder(private val itemHeroesBinding: HeroesListitemBinding) : RecyclerView.ViewHolder(itemHeroesBinding.root){
        fun bind(hero : HeroEntity, heroSelectListener: HeroSelectListener){
            itemHeroesBinding.hero = hero

            itemHeroesBinding.rootView.setOnClickListener {
                heroSelectListener.onHeroSelect(hero = hero, imageView = itemHeroesBinding.heroImageView)
            }
        }
    }

    fun addHeroes(newList : List<HeroEntity>){
        val diffResult = DiffUtil.calculateDiff(HeroesDiffCallback(heroList,newList))
        heroList.clear()
        heroList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val itemViewBinding: HeroesListitemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.heroes_listitem,
            parent,
            false
        )

        return HeroesViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int = heroList.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(heroList[position],heroSelectListener)
    }

    interface HeroSelectListener{
        fun onHeroSelect(hero : HeroEntity, imageView : ImageView)
    }
}