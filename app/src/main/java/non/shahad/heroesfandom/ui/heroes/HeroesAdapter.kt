package non.shahad.heroesfandom.ui.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.data.db.entities.HeroEntity
import non.shahad.heroesfandom.databinding.HeroesListitemBinding
import timber.log.Timber

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

            itemHeroesBinding.root.setOnLongClickListener{
                Timber.tag("HeroesAdapter_").d("$layoutPosition")
                return@setOnLongClickListener true
            }
        }
    }

    fun setHeroesList(newList : List<HeroEntity>){
        val diffResult = DiffUtil.calculateDiff(HeroesDiffCallback(heroList,newList))
        heroList.clear()
        heroList.addAll(newList)
//        notifyDataSetChanged()
        notifyDataSetChanged()
//        diffResult.dispatchUpdatesTo(this)

    }


    fun addHero(paginated : List<HeroEntity>){
        heroList.addAll(paginated)
        notifyItemRangeInserted(heroList.size - paginated.size + 1, paginated.size)
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