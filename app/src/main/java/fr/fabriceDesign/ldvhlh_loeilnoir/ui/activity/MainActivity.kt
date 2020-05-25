package fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.CreatePersoViewModel
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.Injection
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by fabricedesign at 14/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir
 */
class MainActivity : AppCompatActivity() {

    private lateinit var createPersoViewModel: CreatePersoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = Injection.provideViewModelFactory()
        createPersoViewModel = ViewModelProvider(this, factory).get(CreatePersoViewModel::class.java)
        createPersoViewModel.persos.observe(this, Observer { list -> observeList(list) })

        btn_new_game.setOnClickListener { createPerso() }
        btn_game_save.setOnClickListener { saveGame() }
    }

    private fun observeList(list : List<Personnage>) {
        if (list.isEmpty()){
            btn_game_save.visibility = View.GONE
        }
    }

    private fun saveGame() {
        val intent = Intent(this, LoadGame::class.java)
        startActivity(intent)
    }

    private fun createPerso() {
        val intent = Intent(this, CreatePerso::class.java)
        startActivity(intent)
    }

}
