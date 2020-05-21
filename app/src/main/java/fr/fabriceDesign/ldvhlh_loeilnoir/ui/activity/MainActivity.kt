package fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by fabricedesign at 14/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_new_game.setOnClickListener { createPerso() }
        btn_game_save.setOnClickListener { saveGame() }
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
