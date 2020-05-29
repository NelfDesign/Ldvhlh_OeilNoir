package fr.fabriceDesign.ldvhlh_loeilnoir.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.OnClick
import fr.fabriceDesign.ldvhlh_loeilnoir.R
import fr.fabriceDesign.ldvhlh_loeilnoir.dagger.DaggerComponents
import fr.fabriceDesign.ldvhlh_loeilnoir.model.Personnage
import fr.fabriceDesign.ldvhlh_loeilnoir.viewModels.CreatePersoViewModel
import kotlinx.android.synthetic.main.activity_create_perso.*
import kotlinx.android.synthetic.main.tollbar.*
import timber.log.Timber
import java.security.SecureRandom
import javax.inject.Inject

class CreatePerso : AppCompatActivity() {

    //FIELDS
    lateinit var name: String
    lateinit var sexe: String
    lateinit var type: String
    private var courage: Int = 0
    private var force: Int = 0
    private var adresse: Int = 0
    private var charisme: Int = 0
    private var intel: Int = 0
    private var fortune: Int = 0
    private var vie: Int = 30
    private lateinit var persoViewModel : CreatePersoViewModel
    private lateinit var perso : Personnage

    @Inject
    lateinit var factory : ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_perso)
        ButterKnife.bind(this)

        toolbar.title = "Création du personnage"
        setSupportActionBar(toolbar)

        factory = DaggerComponents.create().createFactory()
        persoViewModel = ViewModelProvider(this, factory).get(CreatePersoViewModel::class.java)

        de_depart.setOnClickListener { checkValues()}

    }

    private fun checkValues() {
        name = text_name.text.toString()

        when{
            radio_men.isChecked -> sexe = "Homme"
            radio_women.isChecked -> sexe = "Femme"
        }

        when {
            radio_aventurier.isChecked ->{
                type = if (sexe == "Homme") "Aventurier" else "Aventurière"
            }
            radio_guerrier.isChecked -> {
                type = if (sexe == "Homme") "Guerrier" else "Guerrière"
            }
            radio_nain.isChecked -> {
                type = if (sexe == "Homme") "Nain" else "Naine"
                vie = 35
            }
        }
        perso = Personnage(0, name, sexe, type, courage, force, intel, charisme, adresse, vie ,0 ,0, fortune)
        persoViewModel.createPerso(perso)
        Timber.d("$perso")
        goToParty()
    }

    private fun goToParty() {
        val intent = Intent(this, Game::class.java)
        intent.putExtra("name", perso.name)
        startActivity(intent)
    }

    @OnClick(R.id.de_courage, R.id.de_adresse, R.id.de_charisme, R.id.de_force, R.id.de_intel, R.id.de_fortune, R.id.type_button)
    fun buttonClickFields(view: View){
        when(view.id){
            R.id.de_courage ->{
                courage = defineFields()
                setPersoFields(edit_courage, de_courage, courage)
            }
            R.id.de_adresse -> {
                adresse = defineFields()
                setPersoFields(edit_adresse, de_adresse, adresse)
            }
            R.id.de_charisme ->  {
                charisme = defineFields()
                setPersoFields(edit_charisme, de_charisme, charisme)
            }
            R.id.de_force ->  {
                force = defineFields()
                setPersoFields(edit_force, de_force, force)
            }
            R.id.de_intel ->  {
                intel = defineFields()
                setPersoFields(edit_intel, de_intel, intel)
            }
            R.id.de_fortune ->  {
                fortune = defineFortune()
                setPersoFields(edit_fortune, de_fortune, fortune)
            }
            R.id.type_button -> {
                radio_group.visibility = View.VISIBLE
                checkPersoFields(force, courage, adresse)
            }
        }
    }

    private fun defineFields() : Int {
        val rand = SecureRandom()
        return (rand.nextInt(6) + 1) + 7
    }

    private fun defineFortune() : Int {
        val rand = SecureRandom()
        return ((rand.nextInt(6) + 1) + 6) * 10
    }

    private fun setPersoFields(text : TextView, button : ImageButton, number : Int){
        text.text = number.toString()
        button.visibility = View.GONE
    }

    private fun checkPersoFields(force : Int, courage : Int, adresse : Int){
        if (force >= 12 && courage >= 12){
            radio_guerrier.visibility = View.VISIBLE
        }
        if (force >= 12 && adresse >= 12){
            radio_nain.visibility = View.VISIBLE
        }
    }
}
