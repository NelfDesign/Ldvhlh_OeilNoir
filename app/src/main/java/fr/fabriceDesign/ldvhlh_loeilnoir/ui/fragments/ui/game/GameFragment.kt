package fr.fabriceDesign.ldvhlh_loeilnoir.ui.fragments.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.fabriceDesign.ldvhlh_loeilnoir.R

class GameFragment : Fragment() {

    private lateinit var homeViewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_game, container, false)

        return root
    }
}
