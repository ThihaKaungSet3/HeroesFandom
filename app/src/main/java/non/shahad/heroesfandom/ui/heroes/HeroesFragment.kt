package non.shahad.heroesfandom.ui.heroes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import non.shahad.heroesfandom.R
import non.shahad.heroesfandom.core.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HeroesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heroes, container, false)
    }


}
